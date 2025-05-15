package com.sarc.sarc.core.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;
    private Date validityStart;
    private Date validityEnd;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Discipline> disciplineList;

    public Curriculum(Long id, String subjectName, Date validityStart, Date validityEnd, List<Discipline> disciplineList) {
        this.id = id;
        this.subjectName = subjectName;
        this.validityStart = validityStart;
        this.validityEnd = validityEnd;
        this.disciplineList = disciplineList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}

