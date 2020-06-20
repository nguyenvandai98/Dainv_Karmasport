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
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer model) {
        customerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        customerRepository.remove(id);
    }

    @Override
    public Customer finByEmail(String email) {
        return customerRepository.findbyEmail(email);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        Customer customer = customerRepository.findbyEmail(email);
        if (customer != null) {
            if (password.equals(customer.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
