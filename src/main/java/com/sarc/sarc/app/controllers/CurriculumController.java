package com.sarc.sarc.app.controllers;

import com.sarc.sarc.core.domain.entities.Curriculum;
import com.sarc.sarc.core.services.CurriculumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
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
}
