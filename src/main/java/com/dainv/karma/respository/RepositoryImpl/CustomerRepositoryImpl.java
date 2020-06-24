package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Cart;
import com.dainv.karma.model.Customer;

import com.dainv.karma.respository.IRepository.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Customer> findAll() {
        String query = "select c from Customer c";
        TypedQuery<Customer> customerTypedQuery = entityManager.createQuery(query, Customer.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public Customer findById(Long id) {

        return entityManager.find(Customer.class, id);
    }

    @Override
    public void save(Customer model) {
        entityManager.merge(model);
    }

    @Override
    public void remove(Long id) {
        Customer Customer = entityManager.find(Customer.class, id);
        entityManager.remove(Customer);
    }

    @Override
    public void update(Customer model) {
        entityManager.merge(model);
    }

    @Override
    public Customer findbyEmail(String email) {
        String query = "select c from Customer c where c.email like :email";
        TypedQuery<Customer> customerTypedQuery = entityManager.createQuery(query, Customer.class);
        customerTypedQuery.setParameter("email",email);
        List<Customer> list = customerTypedQuery.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
