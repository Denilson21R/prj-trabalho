package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

@Entity
@Table(name = "company_invite")
public class CompanyInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private User employee;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyInviteStatus status;

    public CompanyInvite() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyInviteStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyInviteStatus status) {
        this.status = status;
    }
}
