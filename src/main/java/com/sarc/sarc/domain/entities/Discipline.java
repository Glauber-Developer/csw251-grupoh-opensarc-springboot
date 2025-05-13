package com.sarc.sarc.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int credits;
    private String program;

    @ElementCollection
    private List<String> bibliograficItem;

    public Discipline(Long id, String name, int credits, String program, List<String> bibliograficItem) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.program = program;
        this.bibliograficItem = bibliograficItem;
    }
}
