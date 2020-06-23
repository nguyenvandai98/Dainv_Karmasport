package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Product;
import com.dainv.karma.respository.IRepository.ProductRepository;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByStatusAndAndCategory(boolean status, String categoryName, Pageable pageable) {
        return productRepository.findAllByStatusAndAndCategory(status,categoryName,pageable);
    }
}
