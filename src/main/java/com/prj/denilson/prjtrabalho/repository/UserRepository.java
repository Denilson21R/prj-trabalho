package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    User authenticateUser(String email);
}
