package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_execute_id")
    private User employee_execute;

    @ManyToOne
    @JoinColumn(name = "employee_schedule_id", nullable = false, updatable = false)
    private User employee_schedule;

    @ManyToMany
    @JoinTable(
            name = "service_schedule",
            joinColumns = @JoinColumn(
                    name = "schedule_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "service_id",
                    referencedColumnName = "id"
            )
    )
    private List<Service> service;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private boolean paid;
    @Column(nullable = false)
    private float amount; //TODO: nao usar tipo primitivo

    @ManyToOne
    @JoinColumn(name = "id_company", nullable = false)
    private Company company;

    public Schedule(long id, LocalDateTime date, User employee_execute, User employee_schedule, List<Service> service, Animal animal, Status status, boolean paid, float amount, Company company) {
        this.id = id;
        this.date = date;
        this.employee_execute = employee_execute;
        this.employee_schedule = employee_schedule;
        this.service = service;
        this.animal = animal;
        this.status = status;
        this.paid = paid;
        this.amount = amount;
        this.company = company;
    }

    public Schedule() {
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setEmployee_execute(User employee_execute) {
        this.employee_execute = employee_execute;
    }

    public void setEmployee_schedule(User employee_schedule) {
        this.employee_schedule = employee_schedule;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public long getId() {
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
