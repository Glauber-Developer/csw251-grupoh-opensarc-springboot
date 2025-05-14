package com.sarc.sarc.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.sarc.sarc.core.domain.entities.ClassEntity;
import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.services.ClassService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name= "Turma", description = "API para gerenciamento de turma")
public class ClassController {
    
    @Autowired
    private ClassService classService;

    @GetMapping
    @Operation(summary= "Listar todas as turmas")
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    @Operation(summary= "Buscar turma por ID")
    public ClassEntity getClassById(@RequestParam Long id) {
        return classService.getClassById(id);
    }

    @GetMapping("/search")
    @Operation(summary= "Buscar turma por número")
    public ClassEntity searchClasses(@RequestParam int classNumber) {
        return classService.getClassByNumber(classNumber);
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Criar nova turma")
    public ClassEntity createClass(@RequestBody ClassEntity classEntity) {
        return classService.createClass(classEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Atualizar turma")
    public ResponseEntity<ClassEntity> updateClass(@RequestParam Long id, @RequestBody ClassEntity classEntity) {
        return classService.updateClass(id, classEntity);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(summary= "Deletar turma")
    public ResponseEntity<Void> deleteClass(@RequestParam Long id) {
        boolean deleted = classService.deleteClass(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{classId}/students")
    @Operation(summary= "Listar alunos de uma turma")
    public List<User> getStudentsByClass(@RequestParam Long classId) {
        ClassEntity classEntity = classService.getClassById(classId);
        return classEntity.getStudents();
    }
    
}
