package com.prj.denilson.prjtrabalho.controller;

import com.prj.denilson.prjtrabalho.model.*;
import com.prj.denilson.prjtrabalho.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @RequestMapping(value = "/schedules/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> GetByClientId(@PathVariable(value = "id") long id)
    {
        List<Schedule> schedules = scheduleRepository.findSchedulesByAnimalOwner(id);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

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

    @RequestMapping(value = "/company/{id}/schedules", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> GetSchedulesByCompany(@PathVariable(value = "id") long id)
    {
        List<Schedule> schedules = scheduleRepository.findSchedulesByCompany(id);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public ResponseEntity<Schedule> Post(@RequestParam Map<String, String> newSchedule)
    {
        Schedule schedule = new Schedule();
        //amount
        schedule.setAmount(Float.parseFloat(newSchedule.get("amount")));
        //date
        String[] dateTime = newSchedule.get("date").split("T");
        schedule.setDate(getLocalDateTime(dateTime));
        //animal
        Animal animal = new Animal();
        animal.setId(Long.parseLong(newSchedule.get("animal")));
        schedule.setAnimal(animal);
        //paid
        schedule.setPaid(false);
        //company
        Company company = new Company();
        company.setId(Integer.parseInt(newSchedule.get("company")));
        schedule.setCompany(company);
        //employee_schedule
        User employee_schedule = new User();
        employee_schedule.setId(Long.parseLong(newSchedule.get("employee_schedule")));
        schedule.setEmployee_schedule(employee_schedule);
        //status
        schedule.setStatus(ScheduleStatus.NOVO);
        //services
        String servicesString = newSchedule.get("services");
        List<Service> newServices = new ArrayList<>();
        convertServicesIdStringToObjects(servicesString, newServices);
        schedule.setService(newServices);
        try {
            scheduleRepository.save(schedule);
            return new ResponseEntity<>(schedule, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static void convertServicesIdStringToObjects(String servicesString, List<Service> newServices) {
        String[] servicesList = servicesString.split(", ");
        for (String newServiceId: servicesList) {
            Service newServiceObject = new Service();
            newServiceObject.setId(Long.parseLong(newServiceId));
            newServices.add(newServiceObject);
        }
    }

    @RequestMapping(value = "/schedule/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Schedule> Put(@PathVariable(value = "id") long id, @RequestParam Map<String, String> newSchedule)
    {
        Optional<Schedule> oldSchedule = scheduleRepository.findById(id);
        if(oldSchedule.isPresent()){
            Schedule schedule = oldSchedule.get();
            if(newSchedule.get("status") != null){
                schedule.setStatus(ScheduleStatus.values()[Integer.parseInt(newSchedule.get("status"))]);
            }
            if(newSchedule.get("paid") != null){
                schedule.setPaid(Boolean.parseBoolean(newSchedule.get("paid")));
            }
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

    private static LocalDateTime getLocalDateTime(String[] dateTime) {
        LocalDate date = getDateByString(dateTime);
        LocalTime time = getTimeByString(dateTime);
        return LocalDateTime.of(date, time);
    }

    private static LocalTime getTimeByString(String[] dateTime) {
        String time = dateTime[1].substring(0, 2) + ":" +dateTime[1].substring(3);
        return LocalTime.of(
                Integer.parseInt(time.substring(0, 2)), //horas
                Integer.parseInt(time.substring(3, 5)) //minutos
        );
    }

    private static LocalDate getDateByString(String[] dateTime) {
        String date = dateTime[0].substring(0, 4) + "/" + dateTime[0].substring(5, 7) + "/" + dateTime[0].substring(8);
        return LocalDate.of(
                Integer.parseInt(date.substring(0, 4)), //ano
                Integer.parseInt(date.substring(5, 7)), //mes
                Integer.parseInt(date.substring(8)) //dia
        );
    }
}
