package com.sarc.sarc.app.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarc.sarc.domain.entities.Building;
import com.sarc.sarc.domain.entities.Resource;
import com.sarc.sarc.domain.entities.Room;
import com.sarc.sarc.infrastructure.BuildingRepository;
import com.sarc.sarc.infrastructure.ResourceRepository;
import com.sarc.sarc.infrastructure.RoomRepository;

/**
 * Serviço para operações relacionadas a salas.
 */
@Service
public class RoomService {
    
    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;
    private final ResourceRepository resourceRepository;
    
    @Autowired
    public RoomService(RoomRepository roomRepository, 
                      BuildingRepository buildingRepository,
                      ResourceRepository resourceRepository) {
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
        this.resourceRepository = resourceRepository;
    }
    
    /**
     * Obtém todas as salas cadastradas
     * @return Lista de salas
     */
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    /**
     * Busca uma sala pelo ID
     * @param id ID da sala
     * @return ResponseEntity com a sala encontrada ou status NOT_FOUND
     */
    public ResponseEntity<Room> getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    /**
     * Busca salas por capacidade mínima
     * @param capacity Capacidade mínima
     * @return Lista de salas com capacidade maior ou igual à especificada
     */
    public List<Room> getRoomsByMinCapacity(Integer capacity) {
        return roomRepository.findByCapacityGreaterThanEqual(capacity);
    }
    
    /**
     * Busca salas por andar em um prédio específico
     * @param buildingId ID do prédio
     * @param floor Número do andar
     * @return Lista de salas no andar especificado do prédio
     */
    public List<Room> getRoomsByBuildingAndFloor(Long buildingId, Integer floor) {
        return roomRepository.findByBuildingIdAndFloor(buildingId, floor);
    }
    
    /**
     * Busca salas que possuem um determinado recurso
     * @param resourceId ID do recurso
     * @return Lista de salas com o recurso especificado
     */
    public List<Room> getRoomsByResource(Long resourceId) {
        return roomRepository.findByResourceId(resourceId);
    }
    
    /**
     * Cria uma nova sala
     * @param room Sala a ser criada
     * @param buildingId ID do prédio onde a sala será criada
     * @return Sala criada
     */
    @Transactional
    public Room createRoom(Room room, Long buildingId) {
        Building building = buildingRepository.findById(buildingId)
            .orElseThrow(() -> new IllegalArgumentException("Prédio não encontrado com ID: " + buildingId));
        
        room.setBuilding(building);
        roomRepository.save(room);
        return room;
    }
    
    /**
     * Atualiza uma sala existente
     * @param id ID da sala a ser atualizada
     * @param roomDetails Novas informações da sala
     * @return ResponseEntity com a sala atualizada ou status NOT_FOUND
     */
    @Transactional
    public ResponseEntity<Object> updateRoom(Long id, Room roomDetails) {
        if (!roomRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        roomDetails.setId(id);
        // Preserva o prédio e recursos existentes
        Optional<Room> existingRoom = roomRepository.findById(id);
        if (existingRoom.isPresent()) {
            roomDetails.setBuilding(existingRoom.get().getBuilding());
            roomDetails.setResources(existingRoom.get().getResources());
        }
        
        Room updatedRoom = roomRepository.save(roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }
    
    /**
     * Remove uma sala pelo ID
     * @param id ID da sala a ser removida
     * @return true se removida com sucesso, false se não encontrada
     */
    @Transactional
    public boolean deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            return false;
        }
        roomRepository.deleteById(id);
        return true;
    }
    
    /**
     * Obtém todos os recursos de uma sala
     * @param roomId ID da sala
     * @return ResponseEntity com o conjunto de recursos ou status NOT_FOUND
     */
    public ResponseEntity<Set<Resource>> getRoomResources(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room.map(r -> ResponseEntity.ok(r.getResources()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    /**
     * Adiciona um recurso a uma sala
     * @param roomId ID da sala
     * @param resourceId ID do recurso
     * @return ResponseEntity com a sala atualizada ou status NOT_FOUND
     */
    @Transactional
    public ResponseEntity<Room> addResourceToRoom(Long roomId, Long resourceId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<Resource> resourceOpt = resourceRepository.findById(resourceId);
        
        if (roomOpt.isPresent() && resourceOpt.isPresent()) {
            Room room = roomOpt.get();
            Resource resource = resourceOpt.get();
            
            room.addResource(resource);
            Room updatedRoom = roomRepository.save(room);
            return ResponseEntity.ok(updatedRoom);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    /**
     * Remove um recurso de uma sala
     * @param roomId ID da sala
     * @param resourceId ID do recurso
     * @return ResponseEntity com a sala atualizada ou status NOT_FOUND
     */
    @Transactional
    public ResponseEntity<Room> removeResourceFromRoom(Long roomId, Long resourceId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<Resource> resourceOpt = resourceRepository.findById(resourceId);
        
        if (roomOpt.isPresent() && resourceOpt.isPresent()) {
            Room room = roomOpt.get();
            Resource resource = resourceOpt.get();
            
            room.removeResource(resource);
            Room updatedRoom = roomRepository.save(room);
            return ResponseEntity.ok(updatedRoom);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
