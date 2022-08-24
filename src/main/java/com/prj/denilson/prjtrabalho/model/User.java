package com.prj.denilson.prjtrabalho.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String phone; //TODO: não usar tipo primitivo
    private String name;
    @Column(updatable = false)
    private UserType type;

    //private UserStatus status; TODO: ver como tratar o delete do usuário

    public User(String email, String password, String phone, String name, UserType type) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.type = type;
        hidePassword();
    }

    public User(){
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
        hidePassword();
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

    public long getId() {
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

    public void hidePassword() {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
