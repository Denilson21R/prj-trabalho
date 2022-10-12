package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.*;
import com.prj.denilson.prjtrabalho.repository.CompanyInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class CompanyInviteController {
    @Autowired
    CompanyInviteRepository companyInviteRepository;

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    public ResponseEntity<CompanyInvite> addInvite(@RequestParam Map<String, String> invite)
    {
        CompanyInvite companyInvite = new CompanyInvite();
        Company company = new Company();
        company.setId(Integer.parseInt(invite.get("company")));
        companyInvite.setCompany(company);
        User employee = new User();
        employee.setId(Integer.parseInt(invite.get("user")));
        companyInvite.setEmployee(employee);
        companyInvite.setStatus(CompanyInviteStatus.ABERTO);
        try {
            CompanyInvite companyInviteSaved = companyInviteRepository.save(companyInvite);
            return new ResponseEntity<>(companyInviteSaved, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/invite/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<CompanyInvite> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newInvite)
    {
        Optional<CompanyInvite> oldInvite = companyInviteRepository.findById(id);
        if(oldInvite.isPresent()){
            CompanyInvite newCompanyInvite = oldInvite.get();
            newCompanyInvite.setStatus(CompanyInviteStatus.values()[Integer.parseInt(newInvite.get("status"))]);
            try {
                companyInviteRepository.save(newCompanyInvite);
                return new ResponseEntity<CompanyInvite>(newCompanyInvite, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/invites/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyInvite>> GetByUserId(@PathVariable(value = "id") long id)
    {
        List<CompanyInvite> invites = companyInviteRepository.findInviterByUserId(id);
        if(!invites.isEmpty()) {
            return new ResponseEntity<>(invites, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/invites/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyInvite>> GetByCompanyId(@PathVariable(value = "id") long id)
    {
        List<CompanyInvite> invites = companyInviteRepository.findInviterByCompanyId(id);
        if(!invites.isEmpty()) {
            return new ResponseEntity<>(invites, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
