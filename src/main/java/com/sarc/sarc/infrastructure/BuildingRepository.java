package com.sarc.sarc.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sarc.sarc.core.domain.entities.Building;

/**
 * Repositório para operações de persistência de prédios.
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    // Busca prédios por nome (case insensitive)
    java.util.List<Building> findByNameContainingIgnoreCase(String name);
    
    // Verifica se existe um prédio com o número especificado
    boolean existsByBuildingNumber(String buildingNumber);

    Optional<Building> findById(Long id);

    public List<Building> findAll();

    public Building save(Building building);

    public boolean existsById(Long id);

    public void deleteById(Long id);
}