package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Annotation;
import com.prj.denilson.prjtrabalho.repository.AnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnnotationController {
    @Autowired
    AnnotationRepository annotationRepository;

    @RequestMapping(value = "/schedule/{id}/annotation", method = RequestMethod.GET)
    public ResponseEntity<List<Annotation>> GetSchedulesByCompany(@PathVariable(value = "id") long id)
    {
        List<Annotation> annotations = annotationRepository.findAnnotationBySchedule(id);
        return new ResponseEntity<>(annotations, HttpStatus.OK);
    }

    @RequestMapping(value = "/annotation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Annotation> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Annotation> annotation = annotationRepository.findById(id);
        if(annotation.isPresent()) {
            return new ResponseEntity<Annotation>(annotation.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/annotation", method = RequestMethod.POST)
    public ResponseEntity<Annotation> Post(@RequestBody Annotation annotation)
    {
        try {
            annotationRepository.save(annotation);
            return new ResponseEntity<Annotation>(annotation, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/annotation/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Annotation> Put(@PathVariable(value = "id") long id, @RequestBody Annotation newAnnotation)
    {
        Optional<Annotation> oldAnnotation = annotationRepository.findById(id);
        if(oldAnnotation.isPresent()){
            Annotation annotation = oldAnnotation.get();
            annotation.setText(newAnnotation.getText());
            annotation.setDate(newAnnotation.getDate());
            try {
                annotationRepository.save(annotation);
                return new ResponseEntity<>(annotation, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/annotation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Annotation> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Annotation> annotation = annotationRepository.findById(id);
        if(annotation.isPresent()){
            annotationRepository.delete(annotation.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
