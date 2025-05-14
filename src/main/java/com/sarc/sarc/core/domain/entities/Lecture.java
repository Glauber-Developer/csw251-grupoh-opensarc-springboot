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

@Getter
@NoArgsConstructor
@Setter
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
}
