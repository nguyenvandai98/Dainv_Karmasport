package com.dainv.karma.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/")
public class GeneralController {
    @GetMapping(value = "dashboard")
    public String dashboard(){
        return "/admin/dashboard";
    }
}
