package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Category;
import com.dainv.karma.respository.IRepository.CategoryRepository;
import com.dainv.karma.sevice.IService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
      return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return  categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.remove(id);
    }
}
