package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Bill_detail;
import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.IRepository.BilDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class BillDetailRepositoryImpl implements BilDetailRepository {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Bill_detail> findAll() {
        return null;
    }

    @Override
    public Bill_detail findById(Long id) {
        return null;
    }

    @Override
    public void save(Bill_detail model) {
    }

    @Override
    public void remove(Long id) {
        Bill_detail bill_detail = entityManager.find(Bill_detail.class, id);
        bill_detail.setBill(null);
        entityManager.remove(bill_detail);
    }

    @Override
    public void update(Bill_detail model) {
    }
}
