package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Bill_detail;
import com.dainv.karma.respository.Repository;


import javax.transaction.Transactional;


@Transactional
public interface BilDetailRepository extends Repository<Bill_detail> {
}
