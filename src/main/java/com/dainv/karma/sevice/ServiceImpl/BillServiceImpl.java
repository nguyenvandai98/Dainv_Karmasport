package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Bill_detail;
import com.dainv.karma.model.Cart;
import com.dainv.karma.respository.IRepository.BilDetailRepository;
import com.dainv.karma.respository.IRepository.BillRepository;
import com.dainv.karma.respository.IRepository.CartRepository;
import com.dainv.karma.respository.Repository;
import com.dainv.karma.sevice.IService.BillDetailService;
import com.dainv.karma.sevice.IService.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BilDetailRepository bilDetailRepository;

    @Autowired
    private CartRepository cartRepository;

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
    public List<Bill> findByStatus(int statusId) {
       return billRepository.findAllByStatusId(statusId);
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public List<Bill> findByCustomer(Long customerId) {
        return  billRepository.findBillByCustomer_CustomerId(customerId);
    }

    @Override
    public void saveBillAndBillDetail(Bill bill, List<Cart> carts) {

          billRepository.save(bill);
            for (Cart cart : carts){

                Bill_detail bill_detail = new Bill_detail();
                bill_detail.setBill(bill);
                bill_detail.setProduct(cart.getProduct());
                bill_detail.setQuantity(cart.getQuantity());
                bill_detail.setPrice(cart.getProduct().getPrice());

                bilDetailRepository.save(bill_detail);
                cartRepository.delete(cart);
            }
        }

}
