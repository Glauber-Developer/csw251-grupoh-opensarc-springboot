package com.sarc.sarc.core.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class class;
    */

    /* 
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;
    */

    private LocalDateTime dateTime; 

    private String observations;

    @Column(nullable = false)
    private String timeSlot;

    public Reservation(Long id, LocalDateTime dateTime, String observations, String timeSlot) {
        this.id = id;
        this.dateTime = dateTime;
        this.observations = observations;
        this.timeSlot = timeSlot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}