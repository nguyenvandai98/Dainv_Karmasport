package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
