package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.respository.IRepository.BilDetailRepository;
import com.dainv.karma.sevice.IService.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BilDetailRepository bilDetailRepository;
    @Override
    public void deleteById(Long id) {
        bilDetailRepository.remove(id);
    }
}
