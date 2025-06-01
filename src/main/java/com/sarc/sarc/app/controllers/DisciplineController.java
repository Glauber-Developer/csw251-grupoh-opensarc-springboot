package com.sarc.sarc.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarc.sarc.core.domain.entities.Discipline;
import com.sarc.sarc.core.services.DisciplineService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/discipline")
@Tag(name= "Disciplinas", description = "API para gerenciamento de disciplinas")
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAllDisciplines(){
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id){
        return disciplineService.getDisciplineById(id);
    }

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineService.createDiscipline(discipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline discipline) {
        return disciplineService.updateDiscipline(id, discipline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Discipline> deleteDiscipline(@PathVariable Long id) {
        return disciplineService.deleteDiscipline(id);
    }
}
