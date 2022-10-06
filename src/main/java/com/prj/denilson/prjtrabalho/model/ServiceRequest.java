package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_request")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private User client;

    @ManyToMany
    @JoinTable(
            name = "service_request_schedule",
            joinColumns = @JoinColumn(
                    name = "schedule_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "service_id",
                    referencedColumnName = "id"
            )
    )
    private List<Service> requested_services;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceRequestStatus status;

    public ServiceRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Service> getRequested_services() {
        return requested_services;
    }

    public void setRequested_services(List<Service> requested_services) {
        this.requested_services = requested_services;
    }

    public Animal getAnimal() {
        return animal;
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

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceRequestStatus status) {
        this.status = status;
    }
}
