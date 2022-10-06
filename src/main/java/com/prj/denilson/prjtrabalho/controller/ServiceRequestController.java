package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class ServiceRequestController {
    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    //TODO: implement endpoints of request
}
