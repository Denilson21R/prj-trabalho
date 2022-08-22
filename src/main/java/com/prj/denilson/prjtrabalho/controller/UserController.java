package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> Get() {
        return userRepository.findAll();
    }
}
