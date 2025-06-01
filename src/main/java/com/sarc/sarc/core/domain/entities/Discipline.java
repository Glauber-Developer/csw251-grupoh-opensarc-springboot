package com.sarc.sarc.core.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<ClassEntity> classEntities;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    public Discipline(Long id, String name, int credits, String program, List<String> bibliograficItem, Curriculum curriculum) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.program = program;
        this.bibliograficItem = bibliograficItem;
        this.curriculum = curriculum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<String> getBibliograficItem() {
        return bibliograficItem;
    }

    public void setBibliograficItem(List<String> bibliograficItem) {
        this.bibliograficItem = bibliograficItem;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
