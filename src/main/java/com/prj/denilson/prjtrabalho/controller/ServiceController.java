package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.Service;
import com.prj.denilson.prjtrabalho.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping(value = "/company/{id}/services", method = RequestMethod.GET)
    public ResponseEntity<List<Service>> Get(@PathVariable(value = "id") long id)
    {
        List<Service> services = serviceRepository.findServicesByCompany(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestBody Service service)
    {
        try {
            serviceRepository.save(service);
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
    public ResponseEntity GetById(@PathVariable(value = "id") long id)
    {
        Optional<Service> service = serviceRepository.findById(id);
        if(service.isPresent()) {
            return new ResponseEntity<>(service.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/service/{id}", method =  RequestMethod.PUT)
    public ResponseEntity Put(@PathVariable(value = "id") long id, @RequestBody Service newService)
    {
        Optional<Service> oldService = serviceRepository.findById(id);
        if(oldService.isPresent()){
            Service service = oldService.get();
            service.setDescription(newService.getDescription());
            service.setValue(newService.getValue());
            serviceRepository.save(service);
            return new ResponseEntity<>(service, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE)
    public ResponseEntity Delete(@PathVariable(value = "id") long id)
    {
        Optional<Service> service = serviceRepository.findById(id);
        if(service.isPresent()){
            serviceRepository.delete(service.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
