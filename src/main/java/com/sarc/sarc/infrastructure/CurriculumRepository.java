package com.sarc.sarc.infrastructure;

import com.sarc.sarc.domain.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    Optional<Curriculum> findById(Long id);
}
