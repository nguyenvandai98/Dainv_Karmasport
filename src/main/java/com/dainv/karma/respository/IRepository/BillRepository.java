package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.Repository;

import java.util.List;

public interface BillRepository extends Repository<Bill> {
    List<Bill> findByCustomer(Long customerId);
    List<Bill> findByStatus(int statusId);
    void saveBillAndBillDetail(Bill bill, List<Cart> carts);
}
