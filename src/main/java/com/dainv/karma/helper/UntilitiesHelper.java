package com.dainv.karma.helper;

import com.dainv.karma.model.Cart;

import java.util.List;

public class UntilitiesHelper {
    public static int gettotal(List<Cart> carts){
        int total = 0;
        for (Cart c: carts) {
            total += (c.getProduct().getPrice() * c.getQuantity());
        }
        return total;
    }

}
