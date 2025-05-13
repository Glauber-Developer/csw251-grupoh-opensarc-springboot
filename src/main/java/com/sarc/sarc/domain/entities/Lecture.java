package com.sarc.sarc.domain.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// import com.sarc.sarc.domain.entities.Reservation;
// import com.sarc.sarc.domain.entities.Room;

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
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Class classEntity;
    private String date;
    private int room; // Type placeholder
    private int reservations; // Type placeholder
    private HashMap<User, Boolean> attendance;
}
