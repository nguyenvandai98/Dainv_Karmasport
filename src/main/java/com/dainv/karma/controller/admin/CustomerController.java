package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Customer;
import com.dainv.karma.sevice.IService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "admin/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "list")
    public String list(Model model, @ModelAttribute("alert")String alert, @ModelAttribute("message")String message){
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        model.addAttribute("customers",customerService.findAll());
        return "admin/customer/list";
    }

    @GetMapping(value = "update")
    public String update(@RequestParam("id")Long id,
                         @RequestParam("status")String status,
                         RedirectAttributes redirectAttributes){
        try{
            Customer customer = customerService.findById(id);
            if(customer == null){
                redirectAttributes.addFlashAttribute("alert", "alert alert-danger");
                redirectAttributes.addFlashAttribute("message", status+" failed");
                return "redirect:/admin/customer/list";
            }
            if(status.equals("active")){
                customer.setStatus(true);
            }else if(status.equals("disable")){
                customer.setStatus(false);
            }
            customerService.save(customer);
            redirectAttributes.addFlashAttribute("alert", "alert alert-success");
            redirectAttributes.addFlashAttribute("message", status+" successfully");
            return "redirect:/admin/customer/list";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("alert", "alert alert-danger");
            redirectAttributes.addFlashAttribute("message", status+" failed");
            return "redirect:/admin/customer/list";
        }

    }


}
