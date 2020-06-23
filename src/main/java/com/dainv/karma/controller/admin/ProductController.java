package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Category;
import com.dainv.karma.model.Product;
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
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/product_detail";
    }

    @PostMapping("create")
    public String creat(Model model, Product product, @RequestParam("img") MultipartFile multipartFile,
                        @RequestParam("img1") MultipartFile multipartFile1,
                        @RequestParam("img2") MultipartFile multipartFile2) {

        try {
            model.addAttribute("product", new Product());

            String filename = multipartFile.getOriginalFilename();
            String filename1 = multipartFile1.getOriginalFilename();
            String filename2 = multipartFile2.getOriginalFilename();

            product.setImage(filename);
            product.setImage1(filename1);
            product.setImage2(filename2);

            productService.save(product);


           transferTo(multipartFile);
           transferTo(multipartFile1);
           transferTo(multipartFile2);

            model.addAttribute("alert", "alert alert-success");
            model.addAttribute("message", "Create product successfully!");
        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("alert", "alert alert-danger");
            model.addAttribute("message", "Create product fail!");
        }
        return "admin/product/product_detail";
    }

    void transferTo(MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        try{
            File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\" + filename);
            if (!file.exists() && filename.length() >0) {
                multipartFile.transferTo(file);
            }
        }catch (Exception e){
           e.printStackTrace();
        }

    }



    @GetMapping(value = "list")
    public String List(Model model, @ModelAttribute("alert") String alert, @ModelAttribute("message") String message) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return "admin/product/list_product";
    }


    @GetMapping(value = "update/{id}")
    public String Update(Model model, @PathVariable("id") Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", productService.findById(id));
            return "admin/product/product_detail";
        } else {
            return "redirect:/404";
        }

    }

    @PostMapping(value = "update")
    public String Update( Product product,
                         @RequestParam("img") MultipartFile multipartFile,
                         @RequestParam("img1") MultipartFile multipartFile1,
                         @RequestParam("img2") MultipartFile multipartFile2,
                         RedirectAttributes ra) {
        try {
            product.setImage(TransferImgUpdate(multipartFile,product));
            product.setImage1(TransferImg1Update(multipartFile1,product));
            product.setImage2(TransferImg2Update(multipartFile2,product));


            productService.save(product);
            ra.addAttribute("alert", "alert alert-success");
            ra.addAttribute("message", "Update product successfully!");
        } catch (Exception e) {
            e.printStackTrace();

            ra.addAttribute("alert", "alert alert-danger");
            ra.addAttribute("message", "update product fail!");
        }
        ra.addAttribute("products", productService.findAll());
        return "redirect:/admin/product/list";
    }
    private String TransferImg2Update(MultipartFile multipartFile,Product prod){
        String imageName2 = null;
        try{
            imageName2 = multipartFile.getOriginalFilename();
            if (imageName2.length() == 0) {
                imageName2 = productService.findById(prod.getProductID()).getImage2();
            } else {
                File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\" + imageName2);
                if (!file.exists()) {
                    multipartFile.transferTo(file);
                }

            }}catch (Exception e){
            e.printStackTrace();
        }
        return imageName2;
    }
    private String TransferImg1Update(MultipartFile multipartFile,Product prod){
        String imageName1 = null;
       try{
         imageName1 = multipartFile.getOriginalFilename();
        if (imageName1.length() == 0) {
          imageName1 = productService.findById(prod.getProductID()).getImage1();
        } else {
            File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\" + imageName1);
            if (!file.exists()) {
                multipartFile.transferTo(file);
            }

        }}catch (Exception e){
           e.printStackTrace();
       }
       return imageName1;
    }
    private String TransferImgUpdate(MultipartFile multipartFile,Product prod){
        String imageName = null;
        try{
            imageName = multipartFile.getOriginalFilename();
            if (imageName.length() == 0) {
                imageName = productService.findById(prod.getProductID()).getImage();
            } else {
                File file = new File("C:\\Users\\DELL\\Dainv_Karmasport\\src\\main\\resources\\static\\upload\\" + imageName);
                if (!file.exists()) {
                    multipartFile.transferTo(file);
                }

            }}catch (Exception e){
            e.printStackTrace();
        }
        return imageName;
    }

    @GetMapping(value = "delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id, RedirectAttributes ra) {

        try {
            productService.remove(id);
            ra.addAttribute("alert", "alert alert-success");
            ra.addAttribute("message", "delete product successfully!");

        } catch (Exception e) {
            e.printStackTrace();

            ra.addAttribute("alert", "alert alert-danger");
            ra.addAttribute("message", "delete product fail!");
        }

        return "redirect:/admin/product/list";
    }

}
