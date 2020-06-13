package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.IRepository.CartRepository;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class CartRepositoryImpl implements CartRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Cart> findAll() {
        String query = "select c from Cart c";
        TypedQuery<Cart> customerTypedQuery = entityManager.createQuery(query, Cart.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public Cart findById(Long id) {

        return entityManager.find(Cart.class, id);
    }

    @Override
    public void save(Cart model) {

        entityManager.merge(model);
    }

    @Override
    public void remove(Long id) {
        Cart Cart = entityManager.find(Cart.class, id);
        entityManager.remove(Cart);
    }

    @Override
    public void update(Cart model) {
        entityManager.merge(model);
    }

    @Override
    public List<Cart> findByCustomerId(Long customerId) {
        String query = "select c from Cart c where c.customer.customerId= :customerId";
        TypedQuery<Cart> customerTypedQuery = entityManager.createQuery(query, Cart.class);
        customerTypedQuery.setParameter("customerId", customerId);
        return customerTypedQuery.getResultList();
    }
}
