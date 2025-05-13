package com.sarc.sarc.infrastructure;

public interface LectureRepository extends org.springframework.data.jpa.repository.JpaRepository<com.sarc.sarc.core.domain.entities.Lecture, Long> {
    // Busca aulas por nome (case insensitive)
    java.util.List<com.sarc.sarc.core.domain.entities.Lecture> findByNameContainingIgnoreCase(String name);

    java.util.Optional<com.sarc.sarc.core.domain.entities.Lecture> findById(Long id);

    java.util.List<com.sarc.sarc.core.domain.entities.Lecture> findByClassNumber(Integer classNumber);
}