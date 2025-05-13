package com.sarc.sarc.app.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Room;
import com.sarc.sarc.infrastructure.BuildingRepository;
import com.sarc.sarc.infrastructure.RoomRepository;

/**
 * Serviço para operações relacionadas a prédios.
 */
@Service
public class BuildingService {
    
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;
    
    @Autowired
    public BuildingService(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }
    
    /**
     * Obtém todos os prédios cadastrados
     * @return Lista de prédios
     */
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }
    
    /**
     * Busca um prédio pelo ID
     * @param id ID do prédio
     * @return ResponseEntity com o prédio encontrado ou status NOT_FOUND
     */
    public ResponseEntity<Building> getBuildingById(Long id) {
        Optional<Building> building = buildingRepository.findById(id);
        return building.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    /**
     * Busca prédios por nome
     * @param name Nome ou parte do nome do prédio
     * @return Lista de prédios encontrados
     */
    public List<Building> getBuildingsByName(String name) {
        return buildingRepository.findByNameContainingIgnoreCase(name);
    }
    
    /**
     * Cria um novo prédio
     * @param building Prédio a ser criado
     * @return Prédio criado
     */
    @Transactional
    public Building createBuilding(Building building) {
        // Verifica se já existe um prédio com o mesmo número
        if (buildingRepository.existsByBuildingNumber(building.getBuildingNumber())) {
            throw new IllegalArgumentException("Já existe um prédio com o número: " + building.getBuildingNumber());
        }
        buildingRepository.save(building);
        return building;
    }
    
    /**
     * Atualiza um prédio existente
     * @param id ID do prédio a ser atualizado
     * @param buildingDetails Novas informações do prédio
     * @return ResponseEntity com o prédio atualizado ou status NOT_FOUND
     */
    @Transactional
    public ResponseEntity<Building> updateBuilding(Long id, Building buildingDetails) {
        if (!buildingRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        buildingDetails.setId(id);
        Building updatedBuilding = buildingRepository.save(buildingDetails);
        return ResponseEntity.ok(updatedBuilding);
    }
    
    /**
     * Remove um prédio pelo ID
     * @param id ID do prédio a ser removido
     * @return true se removido com sucesso, false se não encontrado
     */
    @Transactional
    public boolean deleteBuilding(Long id) {
        if (!buildingRepository.existsById(id)) {
            return false;
        }
        buildingRepository.deleteById(id);
        return true;
    }
    
    /**
     * Obtém todas as salas de um prédio
     * @param buildingId ID do prédio
     * @return Lista de salas do prédio
     */
    public List<Room> getRoomsByBuilding(Long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }
}
