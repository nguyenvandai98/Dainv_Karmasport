package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findCartsByCustomer_CustomerId(Long customerId);
}
