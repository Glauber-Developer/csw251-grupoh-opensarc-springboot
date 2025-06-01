package com.sarc.sarc.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.core.domain.entities.Discipline;

import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<Discipline,Long>{
    Optional<Discipline> findById(Long id);
}