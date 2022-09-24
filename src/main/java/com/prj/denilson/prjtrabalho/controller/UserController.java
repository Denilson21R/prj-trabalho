package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.model.UserType;
import com.prj.denilson.prjtrabalho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    public ResponseEntity PostAuthenticate(@RequestParam Map<String, String> pessoa)
    {
        try {
            User userAuthenticate = userRepository.authenticateUser(pessoa.get("email"));
            if(BCrypt.checkpw(pessoa.get("password"), userAuthenticate.getPassword())){
                return new ResponseEntity<User>(userAuthenticate, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin("http://localhost:4200")
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

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestParam Map<String, String> pessoa)
    {
        User user = new User();
        user.setName(pessoa.get("name"));
        user.setPassword(pessoa.get("password"));
        user.setEmail(pessoa.get("email"));
        user.setPhone(pessoa.get("phone"));
        user.setType(UserType.values()[Integer.parseInt(pessoa.get("type"))]);
        try {
            User newUser = userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/user/{id}", method =  RequestMethod.PATCH)
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