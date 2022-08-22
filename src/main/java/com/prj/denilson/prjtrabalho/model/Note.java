package com.prj.denilson.prjtrabalho.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private final User writer;
    private final LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private final Schedule schedule;

    public Note(String text, User writer, LocalDateTime date, Schedule schedule) {
        this.text = text;
        this.writer = writer;
        this.date = date;
        this.schedule = schedule;
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

    public Schedule getSchedule() {
        return schedule;
    }
}
