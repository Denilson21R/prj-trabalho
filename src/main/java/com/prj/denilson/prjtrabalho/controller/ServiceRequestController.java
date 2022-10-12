package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.*;
import com.prj.denilson.prjtrabalho.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class ServiceRequestController { //TODO: test enpoints
    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @RequestMapping(value = "/service-request", method = RequestMethod.POST)
    public ResponseEntity<ServiceRequest> addInvite(@RequestParam Map<String, String> invite)
    {
        ServiceRequest serviceRequest = new ServiceRequest();
        //animal
        Animal animal = new Animal();
        animal.setId(Long.parseLong(invite.get("animal")));
        serviceRequest.setAnimal(animal);
        //status
        serviceRequest.setStatus(ServiceRequestStatus.ABERTO);
        //datecreate
        serviceRequest.setCreatedDate(LocalDateTime.now());
        //company
        Company company = new Company();
        company.setId(Integer.parseInt(invite.get("company")));
        serviceRequest.setCompany(company);
        //user
        User user = new User();
        user.setId(Integer.parseInt(invite.get("user")));
        serviceRequest.setClient(user);
        //TODO: update service date according to string sended by frontend
        serviceRequest.setServiceDate(LocalDateTime.now());
        try {
            ServiceRequest newServiceRequest = serviceRequestRepository.save(serviceRequest);
            return new ResponseEntity<>(newServiceRequest, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/service-request/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<ServiceRequest> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newRequest)
    {
        Optional<ServiceRequest> oldRequest = serviceRequestRepository.findById(id);
        if(oldRequest.isPresent()){
            ServiceRequest newServiceRequest = oldRequest.get();
            Animal animal = new Animal();
            //animal
            animal.setId(Long.parseLong(newRequest.get("animal")));
            newServiceRequest.setAnimal(animal);
            //status
            newServiceRequest.setStatus(ServiceRequestStatus.values()[Integer.parseInt(newRequest.get("status"))]);
            //TODO: update service date according to string sended by frontend
            newServiceRequest.setServiceDate(LocalDateTime.now());
            try {
                serviceRequestRepository.save(newServiceRequest);
                return new ResponseEntity<>(newServiceRequest, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/service-request/{id}/services", method =  RequestMethod.PATCH)
    public ResponseEntity<ServiceRequest> Patch(@PathVariable(value = "id") long id, @RequestParam List<String> services)
    {
        System.out.println(services.toString());
        Optional<ServiceRequest> oldRequest = serviceRequestRepository.findById(id);
        if(oldRequest.isPresent()){
            ServiceRequest newServiceRequest = oldRequest.get();
            List<Service> newServices = new ArrayList<Service>();
            for (String newServiceId: services) {
                Service newServiceObject = new Service();
                newServiceObject.setId(Long.parseLong(newServiceId));
                newServices.add(newServiceObject);
            }
            newServiceRequest.setRequested_services(newServices);
            System.out.println(newServiceRequest.toString());
            try {
                ServiceRequest serviceSaved = serviceRequestRepository.save(newServiceRequest);
                return new ResponseEntity<>(serviceSaved, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/requests/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceRequest>> GetByCompanyId(@PathVariable(value = "id") long id)
    {
        List<ServiceRequest> invites = serviceRequestRepository.findRequestsByCompanyId(id);
        if(!invites.isEmpty()) {
            return new ResponseEntity<>(invites, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/requests/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceRequest>> GetByUserId(@PathVariable(value = "id") long id)
    {
        List<ServiceRequest> requests = serviceRequestRepository.findRequestsByUserId(id);
        if(!requests.isEmpty()) {
            return new ResponseEntity<>(requests, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
