package com.sarc.sarc.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.sarc.sarc.core.domain.entities.Lecture;
import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.services.LectureService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/lectures")
@Tag(name= "Aulas", description = "API para gerenciamento de aulas")
public class LecturesController {
    
    @Autowired
    private LectureService lectureService;

    @GetMapping
    @Operation(summary= "Listar todas as aulas")
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/{id}")
    @Operation(summary= "Buscar aula por ID")
    public Lecture getLectureById(@PathVariable Long id) {
        return lectureService.getLectureById(id);
    }

    @GetMapping("/search")
    @Operation(summary= "Buscar aula por número de turma")
    public List<Lecture> searchLectures(@RequestParam int classNumber) {
        return lectureService.getLectureByClassNumber(classNumber);
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Criar nova aula")
    public Lecture createLecture(@RequestBody Lecture lectureEntity) {
        return lectureService.createLecture(lectureEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Atualizar aula")
    public ResponseEntity<Lecture> updateLecture(@PathVariable Long id, @RequestBody Lecture lectureEntity) {
        return lectureService.updateLecture(id, lectureEntity);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Deletar aula")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        boolean deleted = lectureService.deleteLecture(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{lectureId}/students")
    @Operation(summary= "Listar presença de uma aula")
    public HashMap<User, Boolean> getStudentsByLecture(@PathVariable Long lectureId) {
        HashMap<User, Boolean> students = lectureService.getAttendanceByLecture(lectureId);
        if (students.isEmpty()) {
            throw new RuntimeException("Nenhuma lista de presença encontrada");
        }
        return students;
    }
}
