package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.Company;
import com.prj.denilson.prjtrabalho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT u " +
                    "FROM User u " +
                    "JOIN Permission p ON p.user.id = u.id " +
                    "JOIN Company c ON c.id = p.company.id " +
                    "WHERE c.id = ?1")
    List<User> findCompanyUsersByCompanyId(long id);

    @Query(value = "SELECT c FROM Company c WHERE c.status = 0")
    List<Company> getAllActiveCompanies();
}
