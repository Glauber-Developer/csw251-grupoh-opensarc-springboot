package com.sarc.sarc.core.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarc.sarc.core.domain.entities.Class;
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

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class getClassById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public Class getClassByNumber(int classNumber) {
        return classRepository.findByClassNumber(classNumber)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    @Transactional
    public Class createClass(Class classEntity) {
        if (classRepository.existsByClassNumber(classEntity.getClassNumber())) {
            throw new RuntimeException("Já existe uma Turma com esse número");
        }
        classRepository.save(classEntity);
        return classEntity;
    }

    @Transactional
    public ResponseEntity<Class> updateClass(Long id, Class classEntity) {
        Class existingClass = classRepository.findById(id)
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
        Class existingClass = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        classRepository.delete(existingClass);
        return true;
    }

    public List<User> getStudentsInClass(Long classId) {
        Class classEntity = classRepository.findById(classId)
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
