package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Permission;
import com.prj.denilson.prjtrabalho.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class PermissionController {
    @Autowired
    PermissionRepository permissionRepository;

    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestBody Permission permission)
    {
        try {
            permissionRepository.save(permission);
            return new ResponseEntity<>(permission, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public ResponseEntity GetById(@PathVariable(value = "id") long id)
    {
        Optional<Permission> permission = permissionRepository.findPermissionByUser(id); //TODO: find by user id
        if(permission.isPresent()) {
            return new ResponseEntity<>(permission.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/permission/{id}", method =  RequestMethod.PUT)
    public ResponseEntity Put(@PathVariable(value = "id") long id, @RequestBody Permission newPermission)
    {
        Optional<Permission> oldPermission = permissionRepository.findById(id);
        if(oldPermission.isPresent()){
            Permission permission = oldPermission.get();
            permission.setCompany_owner(newPermission.isCompany_owner());
            permission.setCan_add_schedules(newPermission.isCan_add_schedules());
            permission.setCan_add_services(newPermission.isCan_add_services());
            permissionRepository.save(permission);
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public ResponseEntity Delete(@PathVariable(value = "id") long id)
    {
        Optional<Permission> permission = permissionRepository.findById(id);
        if(permission.isPresent()){
            permissionRepository.delete(permission.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
