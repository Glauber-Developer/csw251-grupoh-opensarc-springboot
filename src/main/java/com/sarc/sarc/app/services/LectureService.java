package com.sarc.sarc.app.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarc.sarc.domain.entities.Lecture;
import com.sarc.sarc.domain.entities.User;
import com.sarc.sarc.infrastructure.ClassRepository;
import com.sarc.sarc.infrastructure.LectureRepository;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final ClassRepository classRepository;

    public LectureService(LectureRepository lectureRepository, ClassRepository classRepository) {
        this.lectureRepository = lectureRepository;
        this.classRepository = classRepository;
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    public List<Lecture> getLectureByClassNumber(int classNumber) {
        List<Lecture> lectures = lectureRepository.findByClassNumber(classNumber);
        if (lectures.isEmpty()) {
            throw new RuntimeException("Aula não encontrada");
        }
        return lectures;
    }

    public Lecture createLecture(Lecture lecture) {
        if (lectureRepository.existsById(lecture.getId())) {
            throw new RuntimeException("Já existe uma aula com esse ID");
        }
        lectureRepository.save(lecture);
        return lecture;
    }

    public ResponseEntity<Lecture> updateLecture(Long id, Lecture lecture) {
        Lecture existingLecture = lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        existingLecture.setClassEntity(lecture.getClassEntity());
        existingLecture.setDate(lecture.getDate());
        existingLecture.setRoom(lecture.getRoom());
        existingLecture.setReservations(lecture.getReservations());
        existingLecture.setAttendance(lecture.getAttendance());
        lectureRepository.save(existingLecture);
        return ResponseEntity.ok(existingLecture);
    }

    public boolean deleteLecture(Long id) {
        Lecture existingLecture = lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        lectureRepository.delete(existingLecture);
        return true;
    }

    public HashMap<User, Boolean> getAttendanceByLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        return lecture.getAttendance();
    }
}
