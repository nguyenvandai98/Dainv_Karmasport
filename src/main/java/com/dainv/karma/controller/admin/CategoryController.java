package com.dainv.karma.controller.admin;

import com.dainv.karma.model.Category;
import com.dainv.karma.sevice.IService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "admin/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("create")
    public String create_category(Model model) {
        model.addAttribute("category",new Category());
        return "admin/category/detail_category";
    }

    @PostMapping(value = "create")
    public ModelAndView create_category(@ModelAttribute("category") Category category) {
        System.out.println(category.getCategoryName());
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("admin/category/detail_category");
        modelAndView.addObject("message", "Create category successfully!!! ");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @GetMapping(value = "list")
    public ModelAndView getlist() {
        ModelAndView modelAndView = new ModelAndView("admin/category/category_list");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id")Long id, @RequestParam("name") String name) {

        Category category =categoryService.findById(id).get();
        category.setCategoryName(name);
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/admin/category/category_list");
        modelAndView.addObject("message", "update category successfully!!! ");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/category/category_list");
      try{
          categoryService.remove(id);

          modelAndView.addObject("message", "delete category successfully!!! ");
          modelAndView.addObject("categories", categoryService.findAll());
      }catch (Exception e){
          modelAndView.addObject("message", "delete category fail!!! ");
          modelAndView.addObject("categories", categoryService.findAll());
      }
        return modelAndView;
    }

}
