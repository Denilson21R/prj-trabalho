package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query(value = "SELECT s FROM Service s WHERE id_company = ?1")
    List<Service> findServicesByCompany(long id);
}
