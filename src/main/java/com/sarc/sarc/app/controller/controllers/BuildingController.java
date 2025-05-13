package com.sarc.sarc.app.controller.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarc.sarc.app.controller.services.BuildingService;
import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controlador para operações relacionadas a prédios.
 * Apenas administradores podem manipular prédios.
 */
@RestController
@RequestMapping("/api/buildings")
@Tag(name = "Prédios", description = "API para gerenciamento de prédios")
public class BuildingController {
    
    @Autowired
    private BuildingService buildingService;
    
    @GetMapping
    @Operation(summary = "Listar todos os prédios")
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar prédio por ID")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar prédios por nome")
    public List<Building> searchBuildingsByName(@RequestParam String name) {
        return buildingService.getBuildingsByName(name);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar novo prédio", description = "Apenas administradores podem criar prédios")
    public Building createBuilding(@Validated @RequestBody Building building) {
        return buildingService.createBuilding(building);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar prédio", description = "Apenas administradores podem atualizar prédios")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @Valid @RequestBody Building building) {
        return buildingService.updateBuilding(id, building);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover prédio", description = "Apenas administradores podem remover prédios")
    public ResponseEntity<Building> deleteBuilding(@PathVariable Long id) {
        if (buildingService.deleteBuilding(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{buildingId}/rooms")
    @Operation(summary = "Listar salas de um prédio")
    public List<Room> getRoomsByBuilding(@PathVariable Long buildingId) {
        return buildingService.getRoomsByBuilding(buildingId);
    }
}
