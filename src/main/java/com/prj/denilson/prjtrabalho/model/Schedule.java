package com.prj.denilson.prjtrabalho.model;

import org.hibernate.annotations.Cascade;

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
    private ScheduleStatus scheduleStatus;
    @Column(nullable = false)
    private boolean paid;
    @Column(nullable = false)
    private float amount; //TODO: nao usar tipo primitivo

    @ManyToOne
    @JoinColumn(name = "id_company", nullable = false)
    private Company company;

    public Schedule() {
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public void setStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
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

    public User getEmployee_schedule() {
        return employee_schedule;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public List<Service> getService() {
        return service;
    }

    public Animal getAnimal() {
        return animal;
    }

    public ScheduleStatus getStatus() {
        return scheduleStatus;
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
