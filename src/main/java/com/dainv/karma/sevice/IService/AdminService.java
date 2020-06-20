package com.dainv.karma.sevice.IService;

import com.dainv.karma.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> findAll();

    Optional<Admin> findById(int id);

    Admin findByEmail(String email);

    void Save(Admin admin);

    void delete(int id);

    boolean checkLogin(String email,String password);

}
