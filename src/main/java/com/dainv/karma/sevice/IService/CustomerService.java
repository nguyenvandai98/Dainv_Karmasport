package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Customer;


import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer model);

    void remove(Long id);
    Customer finByEmail(String email);
}
