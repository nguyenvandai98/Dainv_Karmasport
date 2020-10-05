package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Customer;
import com.dainv.karma.respository.IRepository.CustomerRepository;
import com.dainv.karma.sevice.IService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void save(Customer model) {
        customerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer finByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer != null) {
            if (password.equals(customer.getPassword()) && customer.isStatus()) {
                return true;
            }
        }
        return false;
    }
}
