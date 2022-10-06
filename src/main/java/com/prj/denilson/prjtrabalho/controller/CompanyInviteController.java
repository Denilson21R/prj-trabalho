package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.repository.CompanyInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class CompanyInviteController {
    @Autowired
    CompanyInviteRepository companyInviteRepository;

    //TODO: implement endpoints of invite
}
