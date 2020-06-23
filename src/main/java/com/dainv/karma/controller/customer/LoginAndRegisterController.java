package com.dainv.karma.controller.customer;

import com.dainv.karma.model.Admin;
import com.dainv.karma.model.Customer;
import com.dainv.karma.sevice.IService.AdminService;
import com.dainv.karma.sevice.IService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;

    @ModelAttribute("customer")
    public Customer setUpUserForm() {
        return new Customer();
    }

    @GetMapping(value = "/login")
    public String login(@ModelAttribute("alert")String alert, @ModelAttribute("message")String message,Model model) {
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return "customer/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }


    @PostMapping(value = "register")
    public String register(Model model, Customer customer, RedirectAttributes ra) {


        try {
            if (customerService.finByEmail(customer.getEmail()) == null) {
                customerService.save(customer);
                ra.addFlashAttribute("alert", "alert alert-success");
                ra.addFlashAttribute("message", "Account registration successful");
                return "redirect:/login";
            } else {
                model.addAttribute("alert", "alert alert-danger");
                model.addAttribute("message", "Your email has been used");
                model.addAttribute("customer", customer);
                return "customer/register";
            }

        } catch (Exception e) {
            model.addAttribute("alert", "alert alert-danger");
            model.addAttribute("message", "Account registration fail");
            model.addAttribute("customer", new Customer());
            return "customer/register";
        }


    }

    @PostMapping(value = "authentic_login")
    public String login(Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String pass,
    RedirectAttributes ra) {
        Customer customer = customerService.finByEmail(email);
        Admin admin = adminService.findByEmail(email);
        if(customerService.checkLogin(email,pass)){

            customer.setPassword(null);
            session.setAttribute("customer", customer.getCustomerId());
            return "redirect:/home";
        }else if(adminService.checkLogin(email,pass)){
            admin.setPassword(null);
            session.setAttribute("admin", admin.getId());
            return "redirect:/admin/dashboard";
        }
        ra.addAttribute("alert", "alert alert-danger");
        ra.addAttribute("message", "Login fail");
        return "redirect:/login";

    }


}
