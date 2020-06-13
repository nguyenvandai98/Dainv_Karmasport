package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Cart;

import java.util.List;

public interface BillService {
    List<Bill> billList();
    void save(Bill bill);
    List<Bill> findByCustomer(Long customerId);
    void saveBillAndBillDetail(Bill bill, List<Cart> carts);
}
