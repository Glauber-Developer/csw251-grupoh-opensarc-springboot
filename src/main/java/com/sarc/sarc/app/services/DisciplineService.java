package com.sarc.sarc.app.services;

import com.sarc.sarc.domain.Discipline;
import com.sarc.sarc.infrastructure.DisciplineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository){
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getAllDisciplines(){return disciplineRepository.findAll();}

    public ResponseEntity<Discipline> getDisciplineById(Long id){
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        return discipline.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Discipline createDiscipline(Discipline discipline){
        disciplineRepository.save(discipline);
        return discipline;
    }

    public ResponseEntity<Discipline> updateDiscipline(Long id,Discipline discipline){
        if(!disciplineRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        discipline.setId(id);

        return ResponseEntity.ok(disciplineRepository.save(discipline));
    }

    public ResponseEntity<Discipline> deleteDiscipline (Long id){
        if(!disciplineRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        disciplineRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
