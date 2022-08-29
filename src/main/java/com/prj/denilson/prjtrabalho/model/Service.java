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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;


    @Column(nullable = false)
    private float value; //TODO: nao usar primitivo

    public Service() {
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
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
