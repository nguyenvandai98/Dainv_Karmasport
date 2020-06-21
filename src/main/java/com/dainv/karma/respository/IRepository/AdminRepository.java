package com.dainv.karma.respository.IRepository;

import com.dainv.karma.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin,Integer> {
     @Query("select c from Admin c where c.Email= :email")
      Admin findAdminByEmail(String email);
      @Query("select a from  Admin a where a.role > :role")
      List<Admin> findAllByRole(int role);
}
