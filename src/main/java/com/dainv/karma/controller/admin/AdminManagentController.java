package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Admin;
import com.dainv.karma.sevice.IService.AdminService;
import com.sun.mail.imap.protocol.MODSEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/management")
public class AdminManagentController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/create")
    public String creat(Model model, HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("admin");
        if(adminId == null || adminService.findById(adminId).get().getRole() >0){
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("admin", new Admin());
        return "admin/adminManagement/detail";
    }

    @PostMapping(value = "/create")
    public String create(Model model, Admin admin) {
        try {
            if (adminService.findByEmail(admin.getEmail()) == null) {
                admin.setEnable(true);
                admin.setPassword("123");
                admin.setRole(1);
                adminService.Save(admin);
                model.addAttribute("alert", "alert alert-success");
                model.addAttribute("message", "create admin successfully");
            } else {
                model.addAttribute("alert", "alert alert-danger");
                model.addAttribute("message", "create admin failed!!");
            }

        } catch (Exception e) {
            return "redirect:/404";
        }
        return "admin/adminManagement/detail";
    }
    @GetMapping(value = "/enable/{id}")
    public String enable(@PathVariable("id")int id, Model model, RedirectAttributes ra){
        Admin admin = adminService.findById(id).get();
      try{
          if(admin!= null && admin.getRole()==0){
              admin.setEnable(false);
              adminService.Save(admin);
              ra.addFlashAttribute("message","successfully");
              ra.addFlashAttribute("alert", "alert alert-success");
          }else {
              return "redirect:/404";
          }
      }catch (Exception e){
          ra.addFlashAttribute("message","failed");
          ra.addFlashAttribute("alert", "alert alert-danger");
          return "redirect:/admin/management/list";
      }
      return "redirect:/admin/management/list";
    }
    @GetMapping(value = "/active/{id}")
    public String active(@PathVariable("id")int id, Model model, RedirectAttributes ra){
        Optional<Admin> optionalAdmin = adminService.findById(id);
        try{
            if(optionalAdmin.isPresent()){
                Admin admin = optionalAdmin.get();
                admin.setEnable(true);
                adminService.Save(admin);
                ra.addFlashAttribute("message","successfully");
                ra.addFlashAttribute("alert", "alert alert-success");
            }else {
                return "redirect:/404";
            }
        }catch (Exception e){
            ra.addFlashAttribute("message","failed");
            ra.addFlashAttribute("alert", "alert alert-danger");
            return "redirect:/admin/management/list";
        }
        return "redirect:/admin/management/list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpSession session, @ModelAttribute("alert")String alert, @ModelAttribute("message")String mesage){
        Integer adminId = (Integer) session.getAttribute("admin");
        if(adminId == null || adminService.findById(adminId).get().getRole() >0){
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", mesage);

        model.addAttribute("admins", adminService.findALlByRole(0));
        return "admin/adminManagement/list";
    }
}
