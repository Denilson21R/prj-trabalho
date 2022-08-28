package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private final User user;

    @ManyToOne
    @JoinColumn(name= "id_company")
    private Company company;

    @Column(nullable = false)
    private boolean can_login;
    @Column(nullable = false)
    private boolean can_add_services;
    @Column(nullable = false)
    private boolean can_add_schedules;

    @Column(nullable = false)
    private boolean company_owner;

    public Permission(long id, User user, Company company, boolean can_login, boolean can_add_services, boolean can_add_schedules, boolean company_owner) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.can_login = can_login;
        this.can_add_services = can_add_services;
        this.can_add_schedules = can_add_schedules;
        this.company_owner = company_owner;
    }

    public boolean isCompany_owner() {
        return company_owner;
    }

    public void setCompany_owner(boolean company_owner) {
        this.company_owner = company_owner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCan_login(boolean can_login) {
        this.can_login = can_login;
    }

    public void setCan_add_services(boolean can_add_services) {
        this.can_add_services = can_add_services;
    }

    public void setCan_add_schedules(boolean can_add_schedules) {
        this.can_add_schedules = can_add_schedules;
    }

    public User getUser() {
        return user;
    }

    public boolean isCan_login() {
        return can_login;
    }

    public boolean isCan_add_services() {
        return can_add_services;
    }

    public boolean isCan_add_schedules() {
        return can_add_schedules;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
