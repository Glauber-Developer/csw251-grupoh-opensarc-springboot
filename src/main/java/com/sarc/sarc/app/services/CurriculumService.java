package com.sarc.sarc.app.services;

import com.sarc.sarc.domain.Curriculum;
import com.sarc.sarc.infrastructure.CurriculumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumService{
    private final CurriculumRepository curriculumRepository;

    public CurriculumService(CurriculumRepository curriculumRepository){
        this.curriculumRepository = curriculumRepository;
    }

    public List<Curriculum> getAllCurriculums(){return curriculumRepository.findAll();}

    public ResponseEntity<Curriculum> getCurriculumById(Long id){
        Optional<Curriculum> curriculum = curriculumRepository.findById(id);
        return curriculum.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Curriculum createCurriculum(Curriculum curriculum){
        curriculumRepository.save(curriculum);
        return curriculum;
    }

    public ResponseEntity<Curriculum> updateCurriculum(Long id, Curriculum curriculum){
        if(!curriculumRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        curriculum.setId(id);
        return ResponseEntity.ok(curriculumRepository.save(curriculum));
    }

    public ResponseEntity<Curriculum> deleteCurriculum(Long id){
        if(!curriculumRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        curriculumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
