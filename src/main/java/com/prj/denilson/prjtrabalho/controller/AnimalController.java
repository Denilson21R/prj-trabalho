package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.Status;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @RequestMapping(value = "/user/{id}/animals", method = RequestMethod.GET)
    public ResponseEntity<List<Animal>> Get(@PathVariable(value = "id") long id)
    {
        try {
            List<Animal> animals = animalRepository.findAnimalsByUser(id);
            return new ResponseEntity<>(animals, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/animal/{id}", method = RequestMethod.GET)
    public ResponseEntity<Animal> GetById(@PathVariable(value = "id") long id)
    {
        try{
            Optional<Animal> animal = animalRepository.findById(id);
            if (animal.isPresent()) {
                return new ResponseEntity<>(animal.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/animal", method = RequestMethod.POST)
    public ResponseEntity<Animal> Post(@RequestParam Map<String, String> animal)
    {
        if(animal.containsKey("description") && animal.containsKey("name")){
            Animal newAnimal = new Animal();
            newAnimal.setStatus(Status.ATIVO);
            newAnimal.setDescription(animal.get("description"));
            newAnimal.setName(animal.get("name"));
            User owner = new User();
            owner.setId(Long.parseLong(animal.get("owner")));
            newAnimal.setOwner(owner);
            newAnimal.setSpecie(animal.get("specie"));
            try {
                animalRepository.save(newAnimal);
                return new ResponseEntity<>(newAnimal, HttpStatus.CREATED);
            }catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @RequestMapping(value = "/animal/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Animal> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newAnimal)
    {
        Optional<Animal> oldAnimal = animalRepository.findById(id);
        if(oldAnimal.isPresent()){
            Animal animal = oldAnimal.get();
            if(newAnimal.containsKey("name")){
                animal.setName(newAnimal.get("name"));
            }
            if(newAnimal.containsKey("description")){
                animal.setDescription(newAnimal.get("description"));
            }
            if(newAnimal.containsKey("status")){
                animal.setStatus(Status.values()[Integer.parseInt(newAnimal.get("status"))]);
            }
            try {
                animalRepository.save(animal);
                return new ResponseEntity<>(animal, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
