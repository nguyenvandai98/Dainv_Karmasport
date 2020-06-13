package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Category;
import com.dainv.karma.respository.IRepository.CategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        String query = "select c from Category c";
        TypedQuery<Category> customerTypedQuery = entityManager.createQuery(query, Category.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public Category findById(Long id) {

        return entityManager.find(Category.class, id);
    }

    @Override
    public void save(Category model) {

            entityManager.merge(model);
    }

    @Override
    public void remove(Long id) {
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }

    @Override
    public void update(Category model) {
        entityManager.merge(model);
    }
}
