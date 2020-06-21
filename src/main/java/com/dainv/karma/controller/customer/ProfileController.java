package com.dainv.karma.controller.customer;

import com.dainv.karma.helper.UntilitiesHelper;
import com.dainv.karma.model.Customer;
import com.dainv.karma.sevice.IService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String profile(Model model, HttpSession session){
        Long customerId = (Long) session.getAttribute("customer");

        if(customerId == null){
            return "redirect:/404";
        }
        Customer customer = customerService.findById(customerId);
        customer.setPassword("0");

        model.addAttribute("customer", customer);
        return "customer/profile";
    }

    @GetMapping(value = "changepassword")
    public String changepass() {
        return "customer/changepass";
    }

    @PostMapping(value = "change/password")
    public String changepass(Model model, @RequestParam("oldPassword")String oldpass,@RequestParam("newPassword")String newpass,HttpSession session) {
        Long cus = (Long) session.getAttribute("customer");
        if(cus == null){
            return "redirect:/login";
        }
        Customer customer = customerService.findById(cus);
        if(oldpass.equals(customer.getPassword())){
            customer.setPassword(newpass);
            customerService.save(customer);
            model.addAttribute("alert", "alert alert-success");
            model.addAttribute("message","Change password successfully!! \n you need to login again");
            session.invalidate();
        }else{
            model.addAttribute("alert", "alert alert-danger");
            model.addAttribute("message","Password is not correct");
        }
        return "customer/changepass";
    }

    @PostMapping(value = "/update")
    public String update(Model model, Customer customer, HttpSession session, @RequestParam("date")String date) {
        Long customerId = (Long) session.getAttribute("customer");

        if(customerId == null){
            return "redirect:/404";
        }
        try{
            Customer cus = customerService.findById(customerId);
            cus.setCustomerName(customer.getCustomerName());
            cus.setAddress(customer.getAddress());
            if(date.length() >0){
                cus.setBirthday(UntilitiesHelper.stringToDate(date));
            }
            customerService.save(cus);
            model.addAttribute("alert", "alert alert-success");
            model.addAttribute("message","Update your profile successfully");
        }catch (Exception e){
         e.printStackTrace();
        }
        return "customer/profile";
    }

}
