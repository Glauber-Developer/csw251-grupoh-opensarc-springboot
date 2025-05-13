package com.sarc.sarc.app.controllers;

import com.sarc.sarc.app.services.DisciplineService;
import com.sarc.sarc.domain.entities.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discipline")
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
