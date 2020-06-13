package com.dainv.karma.controller.customer;

import com.dainv.karma.sevice.IService.CategoryService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping(value={"home",""})
    public String home(Model model) {
        model.addAttribute("products",productService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "customer/homepage";
    }


     @GetMapping(value = "/404")
     public String error(){
        return "customer/error";
     }



    @GetMapping(value = "/profile/changepassword")
    public String changepass() {
        return "customer/changepass";
    }


    @GetMapping(value = "/product/{id}")
    public String singleProduct(Model model, @PathVariable("id")Long productId){
      model.addAttribute("product",productService.findById(productId));
      return "customer/singleProduct";
    }
}
