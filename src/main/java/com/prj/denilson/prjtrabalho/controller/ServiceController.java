package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Company;
import com.prj.denilson.prjtrabalho.model.Service;
import com.prj.denilson.prjtrabalho.model.Status;
import com.prj.denilson.prjtrabalho.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping(value = "/company/{id}/services", method = RequestMethod.GET)
    public ResponseEntity<List<Service>> Get(@PathVariable(value = "id") long id)
    {
        try {
            List<Service> services = serviceRepository.findServicesByCompany(id);
            return new ResponseEntity<>(services, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public ResponseEntity<Service> Post(@RequestParam Map<String, String> service)
    {
        if(service.containsKey("description") && service.containsKey("value") && service.containsKey("company")) {
            Service newService = new Service();
            newService.setDescription(service.get("description"));
            newService.setValue(Float.parseFloat(service.get("value")));
            Company company = new Company();
            company.setId(Integer.parseInt(service.get("company")));
            newService.setCompany(company);
            newService.setStatus(Status.ATIVO);
            try {
                serviceRepository.save(newService);
                return new ResponseEntity<>(newService, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
    public ResponseEntity<Service> GetById(@PathVariable(value = "id") long id)
    {
        try {
            Optional<Service> service = serviceRepository.findById(id);
            if(service.isPresent()) {
                return new ResponseEntity<>(service.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/service/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Service> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newService)
    {
        if(newService.containsKey("description") && newService.containsKey("value") && newService.containsKey("status")) {
            Optional<Service> oldService = serviceRepository.findById(id);
            if (oldService.isPresent()) {
                Service service = oldService.get();
                service.setDescription(newService.get("description"));
                service.setValue(Float.parseFloat(newService.get("value")));
                service.setStatus(Status.values()[Integer.parseInt(newService.get("status"))]);
                try {
                    serviceRepository.save(service);
                    return new ResponseEntity<>(service, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
