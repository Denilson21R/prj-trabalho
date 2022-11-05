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
        if(permission.containsKey("company") && permission.containsKey("user") && permission.containsKey("company_owner") && permission.containsKey("can_add_schedules") && permission.containsKey("can_add_services")) {
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
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public ResponseEntity<Permission> GetById(@PathVariable(value = "id") long id)
    {
        try{
            Optional<Permission> permission = permissionRepository.findPermissionByUser(id);
            if(permission.isPresent()) {
                return new ResponseEntity<>(permission.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/permission/{id}", method =  RequestMethod.PATCH)
    public ResponseEntity<Permission> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newPermission)
    {
        if(newPermission.containsKey("schedules") && newPermission.containsKey("services")) {
            Optional<Permission> oldPermission = permissionRepository.findById(id);
            if (oldPermission.isPresent()) {
                Permission permission = oldPermission.get();
                permission.setCan_add_schedules(Boolean.parseBoolean(newPermission.get("schedules")));
                permission.setCan_add_services(Boolean.parseBoolean(newPermission.get("services")));
                try {
                    Permission permissionSaved = permissionRepository.save(permission);
                    return new ResponseEntity<>(permissionSaved, HttpStatus.OK);
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
