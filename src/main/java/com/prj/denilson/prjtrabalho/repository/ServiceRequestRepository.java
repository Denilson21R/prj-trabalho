package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    @Query(value = "SELECT r FROM ServiceRequest r WHERE r.client.id = ?1")
    List<ServiceRequest> findRequestsByUserId(long id);

    @Query(value = "SELECT r FROM ServiceRequest r WHERE r.company.id = ?1 AND r.status = 'ABERTO'")
    List<ServiceRequest> findRequestsByCompanyId(long id);
}
