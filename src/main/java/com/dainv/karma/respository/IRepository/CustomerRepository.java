package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Customer;
import com.dainv.karma.respository.Repository;

public interface CustomerRepository extends Repository<Customer> {
    Customer findbyEmail(String email);
}
