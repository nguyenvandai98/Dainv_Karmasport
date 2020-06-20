package com.dainv.karma.controller.customer;

import com.dainv.karma.model.Customer;
import com.dainv.karma.respository.IRepository.ProductRepository;
import com.dainv.karma.sevice.IService.CategoryService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(value={"home",""})
    public String home(Model model, @RequestParam(name = "page", defaultValue="0" )int page,
                                    @RequestParam(name = "category", defaultValue = "all")String category) {

        model.addAttribute("products",productRepository.findAllByStatus(true,PageRequest.of(page,6)));


        model.addAttribute("categories",categoryService.findAll());
        return "customer/homepage";
    }
     @GetMapping(value = "/404")
     public String error(){
        return "customer/error";
     }

     @GetMapping(value = "/logout")
     public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home";
     }

    @GetMapping(value = "/product/{id}")
    public String singleProduct(Model model, @PathVariable("id")Long productId){
      model.addAttribute("product",productService.findById(productId));
      return "customer/singleProduct";
    }
}
