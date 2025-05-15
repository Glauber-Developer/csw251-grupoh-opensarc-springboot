package com.sarc.sarc.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarc.sarc.core.domain.entities.Building;
import com.sarc.sarc.core.domain.entities.Room;
import com.sarc.sarc.infrastructure.BuildingRepository;
import com.sarc.sarc.infrastructure.RoomRepository;

@Service
public class BuildingService {
    
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;
    
    @Autowired
    public BuildingService(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }
    
    public ResponseEntity<Building> getBuildingById(Long id) {
        Optional<Building> building = buildingRepository.findById(id);
        return building.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public List<Building> getBuildingsByName(String name) {
        return buildingRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Building createBuilding(Building building) {
        // Verifica se já existe um prédio com o mesmo número
        if (buildingRepository.existsByBuildingNumber(building.getBuildingNumber())) {
            throw new IllegalArgumentException("Já existe um prédio com o número: " + building.getBuildingNumber());
        }
        buildingRepository.save(building);
        return building;
    }

    @Transactional
    public ResponseEntity<Building> updateBuilding(Long id, Building buildingDetails) {
        if (!buildingRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        buildingDetails.setId(id);
        Building updatedBuilding = buildingRepository.save(buildingDetails);
        return ResponseEntity.ok(updatedBuilding);
    }

    @Transactional
    public boolean deleteBuilding(Long id) {
        if (!buildingRepository.existsById(id)) {
            return false;
        }
        buildingRepository.deleteById(id);
        return true;
    }
    
    public List<Room> getRoomsByBuilding(Long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }
}
