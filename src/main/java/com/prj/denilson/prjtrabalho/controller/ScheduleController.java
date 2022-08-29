package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.Schedule;
import com.prj.denilson.prjtrabalho.model.Service;
import com.prj.denilson.prjtrabalho.model.User;
import com.prj.denilson.prjtrabalho.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public List<Schedule> Get() {
        return scheduleRepository.findAll();
    }

    //TODO: gets pararam de funcionar após o many to many

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    public ResponseEntity<Schedule> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if(schedule.isPresent()) {
            return new ResponseEntity<Schedule>(schedule.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/company/{id}/schedule", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> GetSchedulesByCompany(@PathVariable(value = "id") long id)
    {
        List<Schedule> schedules = scheduleRepository.findSchedulesByCompany(id);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public ResponseEntity Post(@RequestBody Schedule schedule)
    {
        try {
            Schedule Schedule = scheduleRepository.save(schedule);
            return new ResponseEntity<Schedule>(schedule, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/schedule/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Schedule> Put(@PathVariable(value = "id") long id, @RequestBody Schedule newSchedule)
    {
        Optional<Schedule> oldSchedule = scheduleRepository.findById(id);
        if(oldSchedule.isPresent()){
            Schedule schedule = oldSchedule.get();
            schedule.setAmount(newSchedule.getAmount());
            schedule.setDate(newSchedule.getDate());
            schedule.setEmployee_execute(newSchedule.getEmployee_execute());
            schedule.setStatus(newSchedule.getStatus());
            schedule.setPaid(newSchedule.isPaid());
            schedule.setService(newSchedule.getService());
            try {
                scheduleRepository.save(schedule);
                return new ResponseEntity<>(schedule, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity Delete(@PathVariable(value = "id") long id)
    {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if(schedule.isPresent()){
            scheduleRepository.delete(schedule.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
