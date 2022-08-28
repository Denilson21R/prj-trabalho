package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.AnimalRepository;
import com.prj.denilson.prjtrabalho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> Get() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> GetById(@PathVariable(value = "id") long id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestBody User pessoa)
    {
        //pessoa.hidePassword();
        try {
            User user = userRepository.save(pessoa);
            return new ResponseEntity<User>(pessoa, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<User> Put(@PathVariable(value = "id") long id, @RequestBody User newUser)
    {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()){
            User user = oldUser.get();
            user.setName(newUser.getName());
            user.setPhone(newUser.getPhone());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            try {
                userRepository.save(user);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}