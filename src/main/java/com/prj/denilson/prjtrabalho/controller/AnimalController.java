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
        List<Animal> animals = animalRepository.findAnimalsByUser(id);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @RequestMapping(value = "/animal/{id}", method = RequestMethod.GET)
    public ResponseEntity<Animal> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent()) {
            return new ResponseEntity<Animal>(animal.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/animal", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestParam Map<String, String> animal)
    {
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
            return new ResponseEntity<Animal>(newAnimal, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/animal/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Animal> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newAnimal)
    {
        Optional<Animal> oldAnimal = animalRepository.findById(id);
        if(oldAnimal.isPresent()){
            Animal animal = oldAnimal.get();
            if(newAnimal.get("name") != null){
                animal.setName(newAnimal.get("name"));
            }
            if(newAnimal.get("description") != null){
                animal.setDescription(newAnimal.get("description"));
            }
            if(newAnimal.get("status") != null){
                animal.setStatus(Status.values()[Integer.parseInt(newAnimal.get("status"))]);
            }
            animalRepository.save(animal);
            return new ResponseEntity<>(animal, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
