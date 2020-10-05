package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByEmail(String email);
}
