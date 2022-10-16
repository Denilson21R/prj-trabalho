package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Company;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestBody Company company)
    {
        try {
            companyRepository.save(company);
            return new ResponseEntity<Company>(company, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()) {
            return new ResponseEntity<Company>(company.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> GetAllCompanies()
    {
        List<Company> companies = companyRepository.getAllActiveCompanies();
        if(!companies.isEmpty()) {
            return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/company/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Company> Put(@PathVariable(value = "id") long id, @RequestBody Company newCompany)
    {
        Optional<Company> oldCompany = companyRepository.findById(id);
        if(oldCompany.isPresent()){
            Company company = oldCompany.get();
            //company.setCompany_name(newCompany.getCompany_name());
            company.setEmail(newCompany.getEmail());
            //company.setStatus(newCompany.getStatus());
            companyRepository.save(company);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/company/{id}/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> GetCompanyUsersByCompanyId(@PathVariable(value = "id") long id) {
        List<User> users = companyRepository.findCompanyUsersByCompanyId(id);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
