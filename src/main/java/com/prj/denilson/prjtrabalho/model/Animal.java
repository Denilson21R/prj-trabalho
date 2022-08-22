package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User owner;
    private String name;
    private String specie;
    private String description;

    public Animal() {
    }

    public Animal(User owner, String name, String specie, String description) {
        this.owner = owner;
        this.name = name;
        this.specie = specie;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getDescription() {
        return description;
    }
}
