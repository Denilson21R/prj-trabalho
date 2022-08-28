package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_creater")
    private User user_create;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String company_name;

    private String cnpj; //TODO: não usar tipo primitivo

    @Enumerated(EnumType.ORDINAL)
    private CompanyStatus status;


    public Company() {
    }

    public void setUser_create(User user_create) {
        this.user_create = user_create;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public long getId() {
        return id;
    }

    public User getUser_create() {
        return user_create;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setId(int id) {
        this.id = id;
    }
}
