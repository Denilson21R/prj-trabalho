package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private final User user;
    private boolean can_login;
    private boolean can_add_services;
    private boolean can_add_schedules;

    public Permission(User user, boolean can_login, boolean can_add_services, boolean can_add_schedules) {
        this.user = user;
        this.can_login = can_login;
        this.can_add_services = can_add_services;
        this.can_add_schedules = can_add_schedules;
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

    public int getId() {
        return id;
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
}
