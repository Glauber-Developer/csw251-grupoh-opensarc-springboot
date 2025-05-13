package com.sarc.sarc.core.domain.entities;

import java.util.ArrayList;
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

@Getter
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int classNumber;
    private String subject;
    private String teacher;
    private double startHour;
    private double endHour;
    private int seats;
    private String exam;
    private ArrayList<User> students;

    public Class(Long id, int classNumber, String subject, String teacher,
                 double startHour, double endHour, int seats, String exam) {
        this.id = id;
        this.classNumber = classNumber;
        this.subject = subject;
        this.teacher = teacher;
        this.startHour = startHour;
        this.endHour = endHour;
        this.seats = seats;
        this.exam = exam;
        this.students = new ArrayList<>();
    }
}
