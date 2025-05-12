package com.sarc.sarc.app.controller.controllers;


import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para operações relacionadas a prédios.
 * Apenas administradores podem manipular prédios.
 */
@RestController
@RequestMapping("/api/buildings")
@Tag(name = "Prédios", description = "API para gerenciamento de prédios")
public class BuildingController {
    
    private final BuildingService buildingService;
    
    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os prédios")
    public ResponseEntity<List<Building>> getAllBuildings() {
        return ResponseEntity.ok(buildingService.getAllBuildings());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar prédio por ID")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar prédios por nome")
    public ResponseEntity<List<Building>> searchBuildingsByName(@RequestParam String name) {
        return ResponseEntity.ok(buildingService.getBuildingsByName(name));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar novo prédio", description = "Apenas administradores podem criar prédios")
    public ResponseEntity<Building> createBuilding(@Valid @RequestBody Building building) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildingService.createBuilding(building));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar prédio", description = "Apenas administradores podem atualizar prédios")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @Valid @RequestBody Building building) {
        return buildingService.updateBuilding(id, building)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover prédio", description = "Apenas administradores podem remover prédios")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        if (buildingService.deleteBuilding(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{buildingId}/rooms")
    @Operation(summary = "Listar salas de um prédio")
    public ResponseEntity<List<Room>> getRoomsByBuilding(@PathVariable Long buildingId) {
        return ResponseEntity.ok(buildingService.getRoomsByBuilding(buildingId));
    }
}

