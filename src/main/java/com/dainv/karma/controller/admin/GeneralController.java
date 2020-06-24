package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Admin;
import com.dainv.karma.sevice.IService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/")
public class GeneralController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "dashboard")
    public String dashboard(HttpSession session, Model model, @ModelAttribute("message")String message) {
        Integer adminId = (Integer) session.getAttribute("admin");
        if (adminId == null) {
            return "redirect:/login";
        }
        Admin admin = adminService.findById(adminId).get();
        model.addAttribute("message", message);
        session.setAttribute("role",admin.getRole()==0?true:false);
        return "/admin/dashboard";
    }

    @GetMapping(value = "logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "customer/login";
    }

    @GetMapping(value = "changePassword")
    public String changePassword(HttpSession session, @RequestParam("newPassword") String newPassword,
                                 @RequestParam("oldPassword") String oldPassword, RedirectAttributes ra) {
        Integer adminId = (Integer) session.getAttribute("admin");
        if (adminId == null) {
            return "redirect:/login";
        }
        Optional<Admin> admin = adminService.findById(adminId);
        if (admin.isPresent() && admin.get().getPassword().equals(oldPassword)){
            admin.get().setPassword(newPassword);
            adminService.Save(admin.get());
            ra.addFlashAttribute("message","change password successfully");
            return "redirect:/admin/dashboard";

        }else {
            ra.addFlashAttribute("message","change password failed!! \n try again");
            return "redirect:/admin/dashboard";
        }
    }
}
