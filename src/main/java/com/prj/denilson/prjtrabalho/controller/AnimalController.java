package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity Post(@RequestBody Animal animal)
    {
        try {
            animalRepository.save(animal);
            return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/animal/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Animal> Put(@PathVariable(value = "id") long id, @RequestBody Animal newAnimal)
    {
        Optional<Animal> oldAnimal = animalRepository.findById(id);
        if(oldAnimal.isPresent()){
            Animal animal = oldAnimal.get();
            animal.setName(newAnimal.getName());
            animal.setDescription(newAnimal.getDescription());
            animalRepository.save(animal);
            return new ResponseEntity<>(animal, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/animal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Animal> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent()){
            animalRepository.delete(animal.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
