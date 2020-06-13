package com.dainv.karma.controller.customer;

import com.dainv.karma.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            return "redirect:/login";
        }
        model.addAttribute("customer", customer);
        return "customer/profile";

    }

}
