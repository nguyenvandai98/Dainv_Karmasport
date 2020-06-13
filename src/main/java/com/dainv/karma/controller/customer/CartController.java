package com.dainv.karma.controller.customer;

import com.dainv.karma.helper.UntilitiesHelper;
import com.dainv.karma.model.Cart;
import com.dainv.karma.model.Customer;
import com.dainv.karma.model.Product;
import com.dainv.karma.sevice.IService.CartService;
import com.dainv.karma.sevice.IService.CustomerService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/cart")
    public String cart(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer==null){
            return "redirect:/login";
        }
        List<Cart> carts = cartService.findByCustomerId(customer.getCustomerId());
        model.addAttribute("totalmoney",  UntilitiesHelper.gettotal(carts));
       model.addAttribute("carts",carts) ;
        return "customer/cart";
    }

    @GetMapping(value = "/addtocart/{id}")
    public String addToCart(@PathVariable("id")Long proid,Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        Product product = productService.findById(proid);
        Cart cart = new Cart();
        if(customer==null){
            return "redirect:/login";
        }
        List<Cart> cartList = cartService.findByCustomerId(customer.getCustomerId());
        if(addtocart(cartList,proid)){
            if (product.getProductID() != null){
                cart.setCustomer(customer);
                cart.setProduct(product);
                cart.setQuantity(1);
                cartService.save(cart);
            }else{
                return "redirect:/404";
            }
        }
        return "redirect:/home";
    }

    @PostMapping(value = "/cart/update/{id}")
    public String update(@PathVariable("id")Long cartId, @RequestParam("quantity")int quantity){
        Cart cart = cartService.findById(cartId);
       if(cart != null){
           cart.setQuantity(quantity);
           cartService.save(cart);
       }else{
           return "redirect:/404";
       }
        return "redirect:/cart";
    }

    @GetMapping(value = "/cart/delete/{id}")
    public String delete(@PathVariable("id")Long cartId){
        try{
            cartService.remove(cartId);
        }catch (Exception e){
            return "redirect:/404";
        }
        return "redirect:/cart";
    }

    private boolean addtocart(List<Cart> list, Long proid){
        for (Cart cart : list){

            if(cart.getProduct().getProductID() == proid){
                cart.setQuantity(cart.getQuantity()+1);
                cartService.save(cart);
                return false;
            }
        }
        return true;
    }

}
