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

import com.sarc.sarc.core.domain.entities.Curriculum;
import com.sarc.sarc.core.services.CurriculumService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/curriculum")
@Tag(name= "Currículos", description = "API para gerenciamento de currículos")
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;

    @GetMapping
    public List<Curriculum> getAllCurriculums(){
        return curriculumService.getAllCurriculums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curriculum> getDisciplineById(@PathVariable Long id){
        return curriculumService.getCurriculumById(id);
    }

    @PostMapping
    public Curriculum createCurriculum(@RequestBody Curriculum curriculum) {
        return curriculumService.createCurriculum(curriculum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculum> updateCurriculum(@PathVariable Long id, @RequestBody Curriculum curriculum) {
        return curriculumService.updateCurriculum(id, curriculum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curriculum> deleteCurriculum(@PathVariable Long id) {
        return curriculumService.deleteCurriculum(id);
    }

    @PostMapping("/{curriculumId}/assign-discipline/{disciplineId}")
    public ResponseEntity<String> assignDisciplineToCurriculum(@PathVariable Long curriculumId, @PathVariable Long disciplineId){
        curriculumService.disciplineAttribuition(disciplineId, curriculumId);
        return ResponseEntity.ok("Disciplina atribuída com sucesso ao currículo.");
    }
}
