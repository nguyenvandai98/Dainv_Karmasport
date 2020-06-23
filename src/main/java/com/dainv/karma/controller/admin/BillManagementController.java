package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Admin;
import com.dainv.karma.model.Bill;
import com.dainv.karma.model.BillStatus;
import com.dainv.karma.sevice.IService.AdminService;
import com.dainv.karma.sevice.IService.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/bill/")
public class BillManagementController {

    @Autowired
    private BillService billService;
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("bills", billService.findAll());
        return "admin/bill/list_bill";
    }

    @PostMapping(value = "update/status")
    public String update(@RequestParam("id")Long id, @RequestParam("status")int status, HttpSession session){
        Integer adminId = (Integer) session.getAttribute("admin");
        Optional<Admin> optionalAdmin = adminService.findById(adminId);
        StringBuilder stringBuilder = new StringBuilder();


        if(optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            Bill bill  = billService.findById(id);
            BillStatus billStatus  = new BillStatus();

            if(bill != null){
                stringBuilder.append(bill.getDescription()+"\n");
               switch (status){
                   case 2:{
                       billStatus.setId(2);
                       stringBuilder.append("Confirm by: "+ admin.getName()+"("+admin.getId()+")");
                   }
                   case 3:{
                       billStatus.setId(3);
                   }
                   case 4:{
                       billStatus.setId(4);
                       bill.setReceivedDate(new Date());
                   }
                   case 5:{
                       billStatus.setId(5);
                       stringBuilder.append("cancel by: "+ admin.getName()+"("+admin.getId()+")" );
                   }
               }
            }
            bill.setStatus(billStatus);
            bill.setDescription(new String(stringBuilder));
            billService.save(bill);
        }
        return "redirect:/admin/bill/list";
    }
}
