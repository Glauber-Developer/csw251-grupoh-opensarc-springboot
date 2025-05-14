package com.sarc.sarc.core.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarc.sarc.core.domain.entities.ClassEntity;
import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.domain.entities.User.TipoPerfil;
import com.sarc.sarc.infrastructure.ClassRepository;
import com.sarc.sarc.infrastructure.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;

    public ClassService(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public ClassEntity getClassById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public ClassEntity getClassByNumber(int classNumber) {
        return classRepository.findByClassNumber(classNumber)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    @Transactional
    public ClassEntity createClass(ClassEntity classEntity) {
        if (classRepository.existsByClassNumber(classEntity.getClassNumber())) {
            throw new RuntimeException("Já existe uma Turma com esse número");
        }
        classRepository.save(classEntity);
        return classEntity;
    }

    @Transactional
    public ResponseEntity<ClassEntity> updateClass(Long id, ClassEntity classEntity) {
        ClassEntity existingClass = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        existingClass.setClassNumber(classEntity.getClassNumber());
        existingClass.setSubject(classEntity.getSubject());
        existingClass.setTeacher(classEntity.getTeacher());
        existingClass.setStartHour(classEntity.getStartHour());
        existingClass.setEndHour(classEntity.getEndHour());
        existingClass.setSeats(classEntity.getSeats());
        existingClass.setExam(classEntity.getExam());
        classRepository.save(existingClass);
        return ResponseEntity.ok(existingClass);
    }

    @Transactional
    public boolean deleteClass(Long id) {
        ClassEntity existingClass = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        classRepository.delete(existingClass);
        return true;
    }

    public List<User> getStudentsInClass(Long classId) {
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        return classEntity.getStudents();
    }

    // TODO: Implementar método para adicionar estudante a uma Turma
    // @Transactional
    // public ResponseEntity<User> addStudentToClass(Long classId, Long studentId) {
    //     Class classEntity = classRepository.findById(classId)
    //             .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    //     User student = userRepository.findById(studentId)
    //             .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    //     if (student.getTipoPerfil() != TipoPerfil.ALUNO) {
    //         throw new RuntimeException("Usuário não é um estudante");
    //     }
    //     if (classEntity.getStudents().contains(student)) {
    //         throw new RuntimeException("Estudante já está matriculado nesta Turma");
    //     }
    //     classEntity.getStudents().add(student);
    //     classRepository.save(classEntity);
    //     return ResponseEntity.ok(student);
}
