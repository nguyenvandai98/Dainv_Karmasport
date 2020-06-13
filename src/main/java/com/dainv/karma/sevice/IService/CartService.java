package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Cart;
import com.dainv.karma.model.Category;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    Cart findById(Long id);
    void save(Cart model);
    void remove(Long id);
    List<Cart> findByCustomerId(Long id);
}
