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
        Long customerId = (Long) session.getAttribute("customer");
        if(customerId == null){
            return "redirect:/login";
        }
        List<Cart> carts = cartService.findByCustomerId(customerId);
        model.addAttribute("totalmoney",  UntilitiesHelper.gettotal(carts));
       model.addAttribute("carts",carts) ;
        return "customer/cart";
    }

    @GetMapping(value = "/addtocart")
    public String addToCart(@RequestParam("id")Long proid, HttpSession session,
                            @RequestParam(name = "quantity",defaultValue = "1")int quantity){
        Long customerId = (Long) session.getAttribute("customer");
        Product product = productService.findById(proid);
        Cart cart = new Cart();

        if(customerId == null || product ==null){
            return "redirect:/login";
        }

        List<Cart> cartList = cartService.findByCustomerId(customerId);
        if(addtocart(cartList,proid,quantity)){
            if (product.getProductID() != null){
                cart.setCustomer(customerService.findById(customerId));
                cart.setProduct(product);
                cart.setQuantity(quantity);
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

    private boolean addtocart(List<Cart> list, Long proid,int quatity){

        for (Cart cart : list){
            if(cart.getProduct().getProductID() == proid){
                cart.setQuantity(cart.getQuantity()+quatity);
                cartService.save(cart);
                return false;
            }
        }
        return true;
    }

}
