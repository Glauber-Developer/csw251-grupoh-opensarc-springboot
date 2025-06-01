package com.sarc.sarc.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.core.domain.entities.ClassEntity;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByClassNumber(int classNumber);
    
    // Verifica se existe uma aula com o número especificado
    boolean existsByClassNumber(int classNumber);

    Optional<ClassEntity> findById(Long id);

    
}