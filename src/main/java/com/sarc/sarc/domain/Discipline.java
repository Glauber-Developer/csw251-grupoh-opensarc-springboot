package com.sarc.sarc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

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
    @CollectionTable(name = "discipline_bibliography", joinColumns = @JoinColumn(name = "discipline_id"))
    @Column(name = "item")
    private List<String> bibliograficItem;
}
