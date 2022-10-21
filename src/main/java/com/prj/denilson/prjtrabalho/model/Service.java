package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_company", nullable = false, updatable = false)
    private Company company;

    @Column(nullable = false)
    private float value; //TODO: nao usar primitivo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Service() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        return value;
    }
}
