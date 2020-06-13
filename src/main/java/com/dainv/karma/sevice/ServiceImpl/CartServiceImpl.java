package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.IRepository.CartRepository;
import com.dainv.karma.sevice.IService.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(Cart model) {
        cartRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        cartRepository.remove(id);
    }

    @Override
    public List<Cart> findByCustomerId(Long id) {
        return cartRepository.findByCustomerId(id);
    }
}
