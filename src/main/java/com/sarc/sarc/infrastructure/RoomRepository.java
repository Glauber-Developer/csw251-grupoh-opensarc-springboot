package com.sarc.sarc.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sarc.sarc.domain.entities.Room;

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

    public List<Room> findAll();

    public Optional<Room> findById(Long id);

    public Room save(Room room);

    public boolean existsById(Long id);

    public void deleteById(Long id);
}
