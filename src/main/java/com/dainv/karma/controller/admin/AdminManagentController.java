package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Admin;
import com.dainv.karma.sevice.IService.AdminService;
import com.sun.mail.imap.protocol.MODSEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "admin/management")
public class AdminManagentController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/create")
    public String creat(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/adminManagement/detail";
    }

    @PostMapping(value = "/create")
    public String create(Model model, Admin admin) {
        try {
            if (adminService.findByEmail(admin.getEmail()) == null) {
                admin.setEnable(true);
                admin.setPassword("123");
                adminService.Save(admin);
                model.addAttribute("alert", "alert alert-success");
                model.addAttribute("message", "{success}");
            } else {
                model.addAttribute("alert", "alert alert-danger");
                model.addAttribute("message", "{success}");
            }

        } catch (Exception e) {
            return "redirect:/404";
        }
        return "admin/adminManagement/list";
    }
    @GetMapping(value = "/enable/{id}")
    public String enable(@PathVariable("id")int id, Model model){
        Admin admin = adminService.findById(id).get();
      try{
          if(admin!= null){
              admin.setEnable(false);
              adminService.Save(admin);


          }else {
              return "redirect:/404";
          }
      }catch (Exception e){
          return "redirect:/404";
      }
      return "redirect:/admin/management/list";
    }
    @GetMapping(value = "/active/{id}")
    public String active(@PathVariable("id")int id, Model model){
        Optional<Admin> optionalAdmin = adminService.findById(id);
        try{
            if(optionalAdmin.isPresent()){
                Admin admin = optionalAdmin.get();
                admin.setEnable(true);
                adminService.Save(admin);
            }else {
                return "redirect:/404";
            }
        }catch (Exception e){
            return "redirect:/404";
        }
        return "redirect:/admin/management/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("admins", adminService.findAll());
        return "admin/adminManagement/list";
    }

}
