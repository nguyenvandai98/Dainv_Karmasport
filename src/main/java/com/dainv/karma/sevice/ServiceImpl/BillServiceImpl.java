package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.IRepository.BillRepository;
import com.dainv.karma.respository.Repository;
import com.dainv.karma.sevice.IService.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> billList() {
        return billRepository.findAll();
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findByCustomer(Long customerId) {
        return  billRepository.findByCustomer(customerId);
    }

    @Override
    public void saveBillAndBillDetail(Bill bill, List<Cart> carts) {
        billRepository.saveBillAndBillDetail(bill,carts);
    }
}
