package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.CompanyInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyInviteRepository extends JpaRepository<CompanyInvite, Long> {
    @Query(value = "SELECT i FROM CompanyInvite i WHERE i.employee.id = ?1")
    List<CompanyInvite> findInviterByUserId(long id);

    @Query(value = "SELECT i FROM CompanyInvite i WHERE i.company.id = ?1")
    List<CompanyInvite> findInviterByCompanyId(long id);
}
