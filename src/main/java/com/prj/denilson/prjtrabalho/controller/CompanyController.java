package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Company;
import com.prj.denilson.prjtrabalho.model.CompanyStatus;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity<Company> Post(@RequestParam Map<String, String> company)
    {
        if(company.containsKey("name") && company.containsKey("email") && company.containsKey("user")) {
            Company newCompany = new Company();
            newCompany.setStatus(CompanyStatus.ATIVO);
            newCompany.setCompany_name(company.get("name"));
            newCompany.setEmail(company.get("email"));
            if(company.containsKey("cnpj")) {
                newCompany.setCnpj(company.get("cnpj"));
            }
            User user = new User();
            user.setId(Long.parseLong(company.get("user")));
            newCompany.setUser_create(user);
            try {
                companyRepository.save(newCompany);
                return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> GetAllCompanies()
    {
        try{
            List<Company> companies = companyRepository.getAllActiveCompanies();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/company/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Company> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newCompany)
    {
        Optional<Company> oldCompany = companyRepository.findById(id);
        if(newCompany.containsKey("email") && newCompany.containsKey("name")){
            if (oldCompany.isPresent()) {
                Company company = oldCompany.get();
                company.setEmail(newCompany.get("email"));
                company.setCompany_name(newCompany.get("name"));
                if (newCompany.get("cnpj") != null) {
                    company.setCnpj(newCompany.get("cnpj"));
                }
                try {
                    companyRepository.save(company);
                    return new ResponseEntity<>(company, HttpStatus.OK);
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

    @RequestMapping(value = "/company/{id}/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> GetCompanyUsersByCompanyId(@PathVariable(value = "id") long id) {
        try {
            List<User> users = companyRepository.findCompanyUsersByCompanyId(id);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
