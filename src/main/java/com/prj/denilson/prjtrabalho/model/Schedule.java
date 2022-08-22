package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "employee_execute_id")
    private final User employee_execute;
    @ManyToOne
    @JoinColumn(name = "employee_schedule_id")
    private final User employee_schedule;
    private List<Service> service; //TODO: ver como usar listas aqui
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private final Animal animal;
    private Status status; //TODO: ver como retornar Enum na API
    private boolean paid;
    private float amount; //TODO: nao usar tipo primitivo

    public Schedule(LocalDateTime date, User employee_execute, User employee_schedule, List<Service> service, Animal animal, float amount, boolean paid) {
        this.date = date;
        this.employee_execute = employee_execute;
        this.employee_schedule = employee_schedule;
        this.service = service;
        this.animal = animal;
        this.amount = amount;
        this.paid = paid;
        this.status = Status.NOVO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getEmployee_execute() {
        return employee_execute;
    }

    public User getEmployee_schedule() {
        return employee_schedule;
    }

    public List<Service> getService() {
        return service;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPaid() {
        return paid;
    }

    public float getAmount() {
        return amount;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
