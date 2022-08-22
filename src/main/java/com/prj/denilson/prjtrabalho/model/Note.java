package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class Note {
    private int id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private final User writer;
    private final LocalDateTime date;
    @OneToMany
    @JoinColumn(name = "id_schedule")
    private final Schedule schedule;

    public Note(String text, User writer, LocalDateTime date, Schedule schedule) {
        this.text = text;
        this.writer = writer;
        this.date = date;
        this.schedule = schedule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
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

    public Schedule getSchedule() {
        return schedule;
    }
}
