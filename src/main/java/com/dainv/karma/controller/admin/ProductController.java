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

import java.io.File;
import java.io.IOException;
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

        model.addAttribute("alert","alert alert-success");
        model.addAttribute("message","Create product successfully!");
    }
        return "admin/product/product_detail";
    }

    @GetMapping(value = "list")
    public String List(Model model){
        model.addAttribute("products", productService.findAll());
        return "admin/product/list_product";
    }

    @GetMapping(value = "update/{id}")
    public String Update(Model model,@PathVariable("id")Long id){
        model.addAttribute("product",productService.findById(id));
        return "admin/product/product_detail";
    }
    @PostMapping(value = "update")
    public String Update(Model model, Product product, @RequestParam("img")MultipartFile multipartFile){
        try{
            System.out.println(product.getProductID());

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



        model.addAttribute("alert","alert alert-success");
        model.addAttribute("message","Update product successfully!");
    }catch (Exception e){
        e.printStackTrace();

        model.addAttribute("alert","alert alert-danger");
        model.addAttribute("message","update product fail!");
    }
        model.addAttribute("products", productService.findAll());
        return "/admin/product/list_product";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(Model model, @PathVariable("id")Long id){
        try{
            productService.remove(id);
            model.addAttribute("alert","alert alert-success");
            model.addAttribute("message","delete product successfully!");

        }catch (Exception e){
            e.printStackTrace();

            model.addAttribute("alert","alert alert-danger");
            model.addAttribute("message","delete product fail!");
        }
        model.addAttribute("products", productService.findAll());
        return "/admin/product/list_product";
    }

}
