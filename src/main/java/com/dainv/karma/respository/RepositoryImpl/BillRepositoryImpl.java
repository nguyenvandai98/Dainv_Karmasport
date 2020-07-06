package com.dainv.karma.respository.RepositoryImpl;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Bill_detail;
import com.dainv.karma.model.Cart;
import com.dainv.karma.model.Product;
import com.dainv.karma.respository.IRepository.BillRepository;
import com.dainv.karma.sevice.IService.CartService;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class BillRepositoryImpl implements BillRepository {
    @Autowired
    private EntityManager entityManager;



    @Autowired
    private CartService cartService;

    @Override
    public List<Bill> findAll() {
        String query = "select c from Bill c order by c.orderDate desc";
        TypedQuery<Bill> customerTypedQuery = entityManager.createQuery(query, Bill.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public Bill findById(Long id) {
        return entityManager.find(Bill.class, id);
    }

    @Override
    public void save(Bill model) {
           entityManager.merge(model);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Bill model) {

    }


    @Override
    public List<Bill> findByCustomer(Long customerId) {
        String query = "select c from Bill c where c.customer.customerId = :customerId order by c.orderDate desc ";
        TypedQuery<Bill> customerTypedQuery = entityManager.createQuery(query, Bill.class);
        customerTypedQuery.setParameter("customerId",customerId);
        return customerTypedQuery.getResultList();
    }

    @Override
    public List<Bill> findByStatus(int statusId) {
        String query = "select c from Bill c where c.status.id = :statusId  ";
        TypedQuery<Bill> customerTypedQuery = entityManager.createQuery(query, Bill.class);
        customerTypedQuery.setParameter("statusId",statusId);
        return customerTypedQuery.getResultList();
    }

    @Override
    public void saveBillAndBillDetail(Bill bill, List<Cart> carts) {
        entityManager.persist(bill);
        for (Cart cart : carts){

            Bill_detail bill_detail = new Bill_detail();
            bill_detail.setBill(bill);
            bill_detail.setProduct(cart.getProduct());
            bill_detail.setQuantity(cart.getQuantity());
            bill_detail.setPrice(cart.getProduct().getPrice());

            entityManager.persist(bill_detail);
            cartService.remove(cart.getId());
        }
    }
}
