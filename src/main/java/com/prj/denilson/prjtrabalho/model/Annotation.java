package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "annotation")
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, updatable = false)
    private User writer;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;

    public Annotation(String text, User writer, LocalDateTime date, Schedule schedule) {
        this.text = text;
        this.writer = writer;
        this.date = date;
        this.schedule = schedule;
    }

    public Annotation() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getWriter() {
        return writer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
