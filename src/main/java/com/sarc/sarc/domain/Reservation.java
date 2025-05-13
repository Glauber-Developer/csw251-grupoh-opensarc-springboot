package com.sarc.sarc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private String description; 
}