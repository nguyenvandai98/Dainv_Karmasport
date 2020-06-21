package com.dainv.karma.controller.customer;

import com.dainv.karma.helper.UntilitiesHelper;
import com.dainv.karma.model.*;
import com.dainv.karma.sevice.IService.BillService;
import com.dainv.karma.sevice.IService.CartService;
import com.dainv.karma.sevice.IService.CustomerService;
import com.dainv.karma.sevice.IService.ProductService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/checkout")
    public String checkout(Model model, HttpSession session){
        Long customerId = (Long) session.getAttribute("customer");
        if(customerId == null){
            return "redirect:/login";
        }
        List<Cart> carts = cartService.findByCustomerId(customerId);
        model.addAttribute("carts",carts);
        model.addAttribute("bill", new Bill());
        model.addAttribute("customer",customerService.findById(customerId));
        model.addAttribute("totalmoney",  UntilitiesHelper.gettotal(carts));
        return "customer/checkout";
    }

    @PostMapping("/bill/add")
    public String add(HttpSession session,@RequestParam("address")String address, @RequestParam("phone")String phone){
        Long customerId = (Long) session.getAttribute("customer");
        if(customerId == null){
            return "redirect:/login";
        }
        try {

            List<Cart> carts = cartService.findByCustomerId(customerId);
            Bill bill = new Bill();
            bill.setTotalMoney(UntilitiesHelper.gettotal(carts));
            bill.setOrderDate(new Date());
            bill.setStatus(0);
            bill.setAddress(address);
            bill.setPhone(phone);
            bill.setCustomer(customerService.findById(customerId));

            System.out.println(bill);
            billService.saveBillAndBillDetail(bill,carts);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/404";
        }
        return "redirect:/purchase";
    }
    @GetMapping(value = "purchase")
    public String purchase(Model model, HttpSession session){
        Long customerId = (Long) session.getAttribute("customer");
        if(customerId == null){
            return "redirect:/login";
        }
         List<Bill> bills =billService.findByCustomer(customerId);
        model.addAttribute("bills", bills);
        return "customer/purchase";
    }

    @GetMapping(value = "/purchase/bill/update/{id}")
    public String update(@PathVariable("id")Long id){
        Bill bill = billService.findById(id);
        if(bill == null){
            return "redirect:/404";
        }
        bill.setStatus(2);
        billService.save(bill);
        List<Bill_detail> billDetailList = bill.getBill_details();
        for (Bill_detail d: billDetailList) {
            Product pro = d.getProduct();
            pro.setQuantity(pro.getQuantity()+d.getQuantity());
            productService.save(pro);
        }

        return "redirect:/purchase";
    }


}
