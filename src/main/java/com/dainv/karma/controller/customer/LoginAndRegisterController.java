package com.dainv.karma.controller.customer;

import com.dainv.karma.model.Customer;
import com.dainv.karma.sevice.IService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/login")
    public String login() {
        return "customer/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }


    @PostMapping(value = "register")
    public String register(Model model, Customer customer) {


        try {
            if (customerService.finByEmail(customer.getEmail()) == null) {
                customerService.save(customer);
                model.addAttribute("alert", "alert alert-success");
                model.addAttribute("message", "Account registration successful");
                return "customer/login";
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
    public String login(Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("pass") String pass) {
        Customer customer = customerService.finByEmail(email);
        if (customer != null) {
            if (pass.equals(customer.getPassword())) {
                customer.setPassword("1111");
                session.setAttribute("customer", customer);
                return "redirect:/home";
            } else {
                model.addAttribute("alert", "alert alert-danger");
                model.addAttribute("message", "Login fail");
                return "customer/login";
            }

        } else {
            model.addAttribute("alert", "alert alert-danger");
            model.addAttribute("message", "Login fail");
            return "customer/login";

        }

    }


}
