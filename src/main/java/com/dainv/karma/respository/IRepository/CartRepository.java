package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.Repository;

import java.util.List;

public interface CartRepository extends Repository<Cart> {
    List<Cart> findByCustomerId(Long customerId);
}
