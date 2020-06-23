package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select c from Product c where c.status = :status")
    Page<Product> findAllByStatus(boolean status, Pageable pageable);

    @Query("select c from Product c where c.status= :status and c.category.categoryName like :categoryName")
    Page<Product> findAllByStatusAndAndCategory(boolean status,String categoryName, Pageable pageable);
}
