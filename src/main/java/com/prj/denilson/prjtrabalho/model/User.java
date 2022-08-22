package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password; //TODO: hash
    private String phone; //TODO: não usar tipo primitivo
    private String name;
    private UserType type; //TODO: ver como retornar Enum na API

    public User(String email, String password, String phone, String name, UserType type) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    void addToCompany(Company company){
        //TODO: quando o usuário ser adicionado a uma empresa, ele recebe uma permissao
    }

}
