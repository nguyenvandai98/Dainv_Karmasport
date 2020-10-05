package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Bill;
import com.dainv.karma.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findBillByCustomer_CustomerId(Long customerId);
    List<Bill> findAllByStatusId(int statusId);

}
