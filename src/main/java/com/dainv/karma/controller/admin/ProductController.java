package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Category;
import com.dainv.karma.model.Product;
import com.dainv.karma.respository.IRepository.ProductRepository;
import com.dainv.karma.sevice.IService.CategoryService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping(value = "admin/product/")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @ModelAttribute("categories")
    List<Category> categoryList() {
        return categoryService.findAll();
    }


    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/product_detail";
    }
    @PostMapping("create")
    public String creat(Model model, Product product, @RequestParam("img")MultipartFile multipartFile){

    try {
        model.addAttribute("product",new Product());

        String filename = multipartFile.getOriginalFilename();
        product.setImage(filename);
        productService.save(product);

        File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\"+ filename);
        if(!file.exists()){
            multipartFile.transferTo(file);
        }
        model.addAttribute("alert","alert alert-success");
        model.addAttribute("message","Create product successfully!");
    }catch (Exception e){
        e.printStackTrace();

        model.addAttribute("alert","alert alert-danger");
        model.addAttribute("message","Create product fail!");
    }
        return "admin/product/product_detail";
    }

    @GetMapping(value = "list")
    public String List(Model model, @ModelAttribute("alert")String alert, @ModelAttribute("message")String message){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return "admin/product/list_product";
    }

    @GetMapping(value = "update/{id}")
    public String Update(Model model,@PathVariable("id")Long id){
        Product product = productService.findById(id);
        if(product != null){
            model.addAttribute("product",productService.findById(id));
            return "admin/product/product_detail";
        }else {
            return "redirect:/404";
        }

    }
    @PostMapping(value = "update")
    public String Update(Model model, Product product, @RequestParam("img")MultipartFile multipartFile,RedirectAttributes ra){
        try{
        String filename = multipartFile.getOriginalFilename();
        if(filename.length() ==0){
            Product product1 =productService.findById(product.getProductID());
            product.setImage(product1.getImage());
        }else{
            File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\"+ filename);
            if(!file.exists()){
                multipartFile.transferTo(file);
            }
            product.setImage(filename);
        }
        productService.save(product);
        ra.addAttribute("alert","alert alert-success");
        ra.addAttribute("message","Update product successfully!");
    }catch (Exception e){
        e.printStackTrace();

        ra.addAttribute("alert","alert alert-danger");
        ra.addAttribute("message","update product fail!");
    }
        ra.addAttribute("products", productService.findAll());
        return "redirect:/admin/product/list";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(Model model, @PathVariable("id")Long id, RedirectAttributes ra){

        try{
            productService.remove(id);
            ra.addAttribute("alert","alert alert-success");
            ra.addAttribute("message","delete product successfully!");

        }catch (Exception e){
            e.printStackTrace();

            ra.addAttribute("alert","alert alert-danger");
            ra.addAttribute("message","delete product fail!");
        }

        return "redirect:/admin/product/list";
    }

}
