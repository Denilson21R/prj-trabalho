package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.*;
import com.prj.denilson.prjtrabalho.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class ServiceRequestController {
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
        //servicedatetime
        String[] dateTime = invite.get("date").split("T"); //arrays date e time
        LocalDate date = getDateByString(dateTime);
        LocalTime time = getTimeByString(dateTime);
        LocalDateTime dateService = LocalDateTime.of(date, time);
        serviceRequest.setServiceDate(dateService);
        //requestdatetime
        serviceRequest.setCreatedDate(LocalDateTime.now());
        try {
            ServiceRequest newServiceRequest = serviceRequestRepository.save(serviceRequest);
            return new ResponseEntity<>(newServiceRequest, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private static LocalTime getTimeByString(String[] dateTime) {
        String time = dateTime[1].substring(0, 2) + ":" +dateTime[1].substring(3);
        return LocalTime.of(
                Integer.parseInt(dateTime[1].substring(0, 2)), //horas
                Integer.parseInt(dateTime[1].substring(3)) //minutos
        );
    }

    private static LocalDate getDateByString(String[] dateTime) {
        String date = dateTime[0].substring(0, 4) + "/" + dateTime[0].substring(5, 7) + "/" + dateTime[0].substring(8);
        return LocalDate.of(
                Integer.parseInt(dateTime[0].substring(0, 4)), //ano
                Integer.parseInt(dateTime[0].substring(5, 7)), //mes
                Integer.parseInt(dateTime[0].substring(8)) //dia
        );
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
    public ResponseEntity<ServiceRequest> Patch(@PathVariable(value = "id") long id, @RequestParam Map<String, String> services)
    {
        Optional<ServiceRequest> oldRequest = serviceRequestRepository.findById(id);
        if(oldRequest.isPresent()){
            List<Service> newServices = new ArrayList<>();
            ServiceRequest newServiceRequest = oldRequest.get();
            String servicesString = services.get("services");
            String[] servicesList = servicesString.split(", ");
            for (String newServiceId: servicesList) {
                Service newServiceObject = new Service();
                newServiceObject.setId(Long.parseLong(newServiceId));
                newServices.add(newServiceObject);
            }
            newServiceRequest.setRequested_services(newServices);
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
