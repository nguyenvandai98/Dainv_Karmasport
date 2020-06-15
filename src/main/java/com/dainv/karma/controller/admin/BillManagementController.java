package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Bill;
import com.dainv.karma.sevice.IService.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(value = "admin/bill/")
public class BillManagementController {

    @Autowired
    private BillService billService;
    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("bills", billService.findAll());
        return "admin/bill/list_bill";
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable("id")Long id){
        Bill bill  = billService.findById(id);
        // 1- received
        bill.setStatus(1);
        bill.setReceivedDate(new Date());
        billService.save(bill);
        return "redirect:/admin/bill/list";
    }
}
