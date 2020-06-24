package com.dainv.karma.controller.admin;

import com.dainv.karma.model.*;
import com.dainv.karma.sevice.IService.AdminService;
import com.dainv.karma.sevice.IService.BillDetailService;
import com.dainv.karma.sevice.IService.BillService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/bill/")
public class BillManagementController {

    @Autowired
    private BillService billService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BillDetailService billDetailService;


    @GetMapping(value = "list")
    public String list(Model model,@ModelAttribute("alert")String alert,@ModelAttribute("message")String message) {
        model.addAttribute("bills", billService.findAll());
        model.addAttribute("alert",alert);
        model.addAttribute("message",message);
        return "admin/bill/list_bill";
    }

    @PostMapping(value = "update/status")
    public String update(@RequestParam("id") Long id,
                         @RequestParam(value = "status", defaultValue = "0") int status,
                         @RequestParam(value = "note", defaultValue = "")String note, HttpSession session,
                         RedirectAttributes redirectAttributes) {
        Integer adminId = (Integer) session.getAttribute("admin");
        if(adminId ==null){
            return "redirect:/login";
        }
        Optional<Admin> optionalAdmin = adminService.findById(adminId);
        StringBuilder stringBuilder = new StringBuilder();

        try{
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            Bill bill = billService.findById(id);
            BillStatus billStatus = bill.getStatus();


            if (bill != null) {

                switch (status) {
                    case 2: {
                        if(checkQuantityBill(bill.getBill_details())){

                            if(bill.getStatus().getId()==1  ){
                                updateQuantityProductMinus(bill.getBill_details());
                            }
                            bill.setStatus(new BillStatus(2,null));
                        }else{
                            redirectAttributes.addFlashAttribute("alert","alert alert-danger");
                            redirectAttributes.addFlashAttribute("message","In stock items are not enough for this order");
                            return "redirect:/admin/bill/list";
                        }
                        break;
                    }
                    case 3: {
                        if(checkQuantityBill(bill.getBill_details())){

                            if(bill.getStatus().getId()==1  || bill.getStatus().getId()==6){
                                updateQuantityProductMinus(bill.getBill_details());
                            }
                            bill.setStatus(new BillStatus(3,null));
                        }else{
                            redirectAttributes.addFlashAttribute("alert","alert alert-danger");
                            redirectAttributes.addFlashAttribute("message","In stock items are not enough for this order");
                            return "redirect:/admin/bill/list";
                        }
                        break;
                    }
                    case 4: {
                        bill.setStatus(new BillStatus(4,null));
                        bill.setReceivedDate(new Date());
                        break;
                    }
                    case 5: {

                        if(bill.getStatus().getId() !=1 ){
                            updateQuantityProductPlus(bill.getBill_details());
                        }
                        bill.setStatus(new BillStatus(5,null));
                        break;
                    }
                    case 6: {

                        if(bill.getStatus().getId() !=1 ){
                            updateQuantityProductPlus(bill.getBill_details());
                        }
                        bill.setStatus(new BillStatus(6,null));
                        break;
                    }
                    default:
                        break;
                }
            }


            stringBuilder.append(bill.getDescription() + "\n");
            if(note.trim().length() >0){
                stringBuilder.append("note("+admin.getName()+"-"+adminId+"): "+ note +"\n");
            }

            bill.setDescription(new String(stringBuilder));
            billService.save(bill);
            redirectAttributes.addFlashAttribute("alert","alert alert-success");
            redirectAttributes.addFlashAttribute("message","Successfully!!");
        }}catch (Exception e){
            e.printStackTrace();
            return "redirect:/404";
        }
        return "redirect:/admin/bill/list";
    }

    @GetMapping(value = "removeProduct")
    public String remove( RedirectAttributes redirectAttributes,
                         @RequestParam("id")Long id){
       try {
           billDetailService.deleteById(id);

       }catch (Exception e){
           redirectAttributes.addFlashAttribute("alert","alert alert-danger");
           redirectAttributes.addFlashAttribute("message","delete failed");
        }
        return "redirect:/admin/bill/list";
    }

     void updateQuantityProductPlus(List<Bill_detail> bill_details) {
        for (Bill_detail bill_detail : bill_details) {
            Product product = bill_detail.getProduct();
            product.setQuantity(product.getQuantity()+ bill_detail.getQuantity());
            productService.save(product);
        }
    }
    void updateQuantityProductMinus(List<Bill_detail> bill_details) {
        for (Bill_detail bill_detail : bill_details) {
            Product product = bill_detail.getProduct();
            product.setQuantity(product.getQuantity()- bill_detail.getQuantity());
            productService.save(product);
        }
    }
    boolean checkQuantityBill(List<Bill_detail> bill_details){
        for (Bill_detail bill_detail: bill_details){
            int billQuantity = bill_detail.getQuantity();
            int productQuantity = bill_detail.getProduct().getQuantity();
            if(billQuantity > productQuantity){
                return false;
            }
        }
        return true;
    }

}
