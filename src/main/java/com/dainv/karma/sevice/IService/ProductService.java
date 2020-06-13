package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Category;
import com.dainv.karma.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);
}
