package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Company;
import com.prj.denilson.prjtrabalho.model.Permission;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class PermissionController {
    @Autowired
    PermissionRepository permissionRepository;

    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public ResponseEntity<Permission> Post(@RequestParam Map<String, String> permission)
    {
        Permission newPermission = new Permission();
        Company newCompany = new Company();
        newCompany.setId(Integer.parseInt(permission.get("company")));
        newPermission.setCompany(newCompany);
        User user = new User();
        user.setId(Long.parseLong(permission.get("user")));
        newPermission.setUser(user);
        newPermission.setCompany_owner(Boolean.parseBoolean(permission.get("company_owner")));
        newPermission.setCan_add_schedules(Boolean.parseBoolean(permission.get("can_add_schedules")));
        newPermission.setCan_add_services(Boolean.parseBoolean(permission.get("can_add_services")));
        try {
            permissionRepository.save(newPermission);
            return new ResponseEntity<>(newPermission, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @RequestMapping(value = "/permission/{id}", method =  RequestMethod.PATCH)
    public ResponseEntity Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newPermission)
    {
        Optional<Permission> oldPermission = permissionRepository.findById(id);
        if(oldPermission.isPresent()){
            Permission permission = oldPermission.get();
            permission.setCan_add_schedules(Boolean.parseBoolean(newPermission.get("schedules")));
            permission.setCan_add_services(Boolean.parseBoolean(newPermission.get("services")));
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
