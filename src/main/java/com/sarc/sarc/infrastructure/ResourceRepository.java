package com.sarc.sarc.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarc.sarc.core.domain.entities.Resource;

/**
 * Repositório para operações de persistência de recursos.
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    // Busca recursos por tipo
    List<Resource> findByType(com.sarc.sarc.common.enums.ResourceType type);
    
    // Busca recursos por status
    List<Resource> findByStatus(com.sarc.sarc.common.enums.ResourceStatus status);
    
    // Busca recursos por nome (case insensitive)
    List<Resource> findByNameContainingIgnoreCase(String name);
    
    // Busca recursos por característica
    @org.springframework.data.jpa.repository.Query("SELECT r FROM Resource r JOIN r.characteristics c WHERE c = :characteristic")
    List<Resource> findByCharacteristic(@org.springframework.data.repository.query.Param("characteristic") String characteristic);

    public Optional<Resource> findById(Long resourceId);
}