package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Bill_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

@Repository
@Transactional
public interface BilDetailRepository extends JpaRepository<Bill_detail,Long> {
}
