package com.sarc.sarc.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.core.domain.entities.Curriculum;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    Optional<Curriculum> findById(Long id);
}
