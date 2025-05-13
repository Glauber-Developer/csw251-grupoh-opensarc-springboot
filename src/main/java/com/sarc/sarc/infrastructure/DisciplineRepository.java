package com.sarc.sarc.infrastructure;
import com.sarc.sarc.domain.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<Discipline,Long>{
    Optional<Discipline> findById(Long id);
}