package com.sarc.sarc.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.core.domain.entities.Class;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
    // Busca aulas por nome (case insensitive)
    java.util.List<Class> findByNameContainingIgnoreCase(String name);

    Optional<Class> findByClassNumber(int classNumber);
    
    // Verifica se existe uma aula com o número especificado
    boolean existsByClassNumber(int classNumber);

    Optional<Class> findById(Long id);

    
}