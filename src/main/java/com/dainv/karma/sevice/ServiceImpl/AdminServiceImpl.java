package com.dainv.karma.sevice.ServiceImpl;

import com.dainv.karma.model.Admin;
import com.dainv.karma.respository.IRepository.AdminRepository;
import com.dainv.karma.sevice.IService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findById(int id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin findByEmail(String email) {
       return  adminRepository.findAdminByEmail(email);
    }

    @Override
    public void Save(Admin admin) {
         adminRepository.save(admin);
    }

    @Override
    public void delete(int id) {
            adminRepository.deleteById(id);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin != null) {
            if (password.equals(admin.getPassword()) && admin.isEnable()== true) {
                return true;
            }
        }
        return false;
    }
}
