package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Category;
import com.dainv.karma.model.Product;
import com.dainv.karma.respository.IRepository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Product> findAll() {
        String query = "select c from Product c";
        TypedQuery<Product> customerTypedQuery = entityManager.createQuery(query, Product.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public Product findById(Long id) {

        return entityManager.find(Product.class, id);
    }

    @Override
    public void save(Product model) {

        entityManager.merge(model);
    }

    @Override
    public void remove(Long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    @Override
    public void update(Product model) {
        entityManager.merge(model);
    }
}
