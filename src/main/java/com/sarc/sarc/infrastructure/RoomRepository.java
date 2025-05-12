package com.sarc.sarc.infrastructure;

import org.springframework.stereotype.Repository;

import com.sarc.sarc.domain.Room;

/**
 * Repositório para operações de persistência de salas.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Busca salas por prédio
    java.util.List<Room> findByBuildingId(Long buildingId);
    
    // Busca salas por capacidade mínima
    java.util.List<Room> findByCapacityGreaterThanEqual(Integer capacity);
    
    // Busca salas por andar em um prédio específico
    java.util.List<Room> findByBuildingIdAndFloor(Long buildingId, Integer floor);
    
    // Busca salas que possuem um determinado recurso
    @Query("SELECT r FROM Room r JOIN r.resources res WHERE res.id = :resourceId")
    java.util.List<Room> findByResourceId(@Param("resourceId") Long resourceId);
}
