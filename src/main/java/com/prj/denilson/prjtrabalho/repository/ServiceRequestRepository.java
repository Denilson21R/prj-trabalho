package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
