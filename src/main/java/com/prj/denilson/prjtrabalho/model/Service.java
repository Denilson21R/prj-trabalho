package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float value; //TODO: nao usar primitivo

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Service(String description, float value) {
        this.description = description;
        this.value = value;
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
