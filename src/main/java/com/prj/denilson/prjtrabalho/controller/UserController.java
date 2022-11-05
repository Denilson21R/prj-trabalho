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

@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    public ResponseEntity<User> PostAuthenticate(@RequestParam Map<String, String> pessoa)
    {
        try {
            User userAuthenticate = userRepository.getUserByEmail(pessoa.get("email"));
            if(BCrypt.checkpw(pessoa.get("password"), userAuthenticate.getPassword())){
                return new ResponseEntity<>(userAuthenticate, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> GetById(@PathVariable(value = "id") long id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> Post(@RequestParam Map<String, String> pessoa)
    {
        if(pessoa.containsKey("name") && pessoa.containsKey("password") && pessoa.containsKey("email") && pessoa.containsKey("type")){
            User user = new User();
            user.setName(pessoa.get("name"));
            user.setPassword(pessoa.get("password"));
            user.setEmail(pessoa.get("email"));
            user.setType(UserType.values()[Integer.parseInt(pessoa.get("type"))]);
            if(pessoa.containsKey("phone")){
                user.setPhone(pessoa.get("phone"));
            }
            try {
                User newUser = userRepository.save(user);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            }catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/user/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<User> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newUser)
    {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent() && newUser.containsKey("name") && newUser.containsKey("email")){ //cenario ideal
            User user = oldUser.get();
            user.setName(newUser.get("name"));
            user.setEmail(newUser.get("email"));
            if(newUser.containsKey("phone")){
                user.setPhone(newUser.get("phone"));
            }
            if(newUser.containsKey("password")){
                user.setPassword(newUser.get("password"));
            }
            try {
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else if(oldUser.isPresent()){ //faltam dados obrigatorios
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }else{ // usuario nao encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}