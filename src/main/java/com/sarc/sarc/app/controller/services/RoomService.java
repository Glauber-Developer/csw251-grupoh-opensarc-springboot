package com.sarc.sarc.app.controller.services;

import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Resource;
import com.sarc.sarc.domain.Room;
import com.sarc.sarc.infrastructure.BuildingRepository;
import com.sarc.sarc.infrastructure.ResourceRepository;
import com.sarc.sarc.infrastructure.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
     * @return Sala encontrada ou vazio
     */
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
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
        return roomRepository.save(room);
    }
    
    /**
     * Atualiza uma sala existente
     * @param id ID da sala a ser atualizada
     * @param roomDetails Novas informações da sala
     * @return Sala atualizada ou vazio se não encontrada
     */
    @Transactional
    public Optional<Room> updateRoom(Long id, Room roomDetails) {
        return roomRepository.findById(id).map(room -> {
            room.setName(roomDetails.getName());
            room.setCapacity(roomDetails.getCapacity());
            room.setFloor(roomDetails.getFloor());
            // Não atualiza o prédio nem recursos aqui
            return roomRepository.save(room);
        });
    }
    
    /**
     * Remove uma sala pelo ID
     * @param id ID da sala a ser removida
     * @return true se removida com sucesso, false se não encontrada
     */
    @Transactional
    public boolean deleteRoom(Long id) {
        return roomRepository.findById(id).map(room -> {
            roomRepository.delete(room);
            return true;
        }).orElse(false);
    }
    
    /**
     * Adiciona um recurso a uma sala
     * @param roomId ID da sala
     * @param resourceId ID do recurso
     * @return Sala atualizada com o novo recurso
     */
    @Transactional
    public Optional<Room> addResourceToRoom(Long roomId, Long resourceId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<Resource> resourceOpt = resourceRepository.findById(resourceId);
        
        if (roomOpt.isPresent() && resourceOpt.isPresent()) {
            Room room = roomOpt.get();
            Resource resource = resourceOpt.get();
            
            room.addResource(resource);
            return Optional.of(roomRepository.save(room));
        }
        
        return Optional.empty();
    }
    
    /**
     * Remove um recurso de uma sala
     * @param roomId ID da sala
     * @param resourceId ID do recurso
     * @return Sala atualizada sem o recurso
     */
    @Transactional
    public Optional<Room> removeResourceFromRoom(Long roomId, Long resourceId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<Resource> resourceOpt = resourceRepository.findById(resourceId);
        
        if (roomOpt.isPresent() && resourceOpt.isPresent()) {
            Room room = roomOpt.get();
            Resource resource = resourceOpt.get();
            
            room.removeResource(resource);
            return Optional.of(roomRepository.save(room));
        }
        
        return Optional.empty();
    }
    
    /**
     * Obtém todos os recursos de uma sala
     * @param roomId ID da sala
     * @return Conjunto de recursos da sala ou vazio se não encontrada
     */
    public Optional<Set<Resource>> getRoomResources(Long roomId) {
        return roomRepository.findById(roomId).map(Room::getResources);
    }
}
