package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    void save(Category category);

    void remove(Long id);
}
