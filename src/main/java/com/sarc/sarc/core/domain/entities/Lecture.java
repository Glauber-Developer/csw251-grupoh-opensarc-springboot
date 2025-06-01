package com.sarc.sarc.core.domain.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sarc.sarc.core.domain.entities.Reservation;
import com.sarc.sarc.core.domain.entities.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClassEntity classEntity;
    private String date;
    private String content;
    private int room; // Type placeholder
    private int reservations; // Type placeholder
    @OneToOne
    private Attendance attendance;

    public Lecture(Long id, ClassEntity classEntity, String date, String content, int room, int reservations, Attendance attendance) {
        this.id = id;
        this.classEntity = classEntity;
        this.date = date;
        this.content = content;
        this.room = room;
        this.reservations = reservations;
        this.attendance = attendance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
