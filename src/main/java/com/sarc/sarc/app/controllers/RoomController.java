package com.sarc.sarc.app.controllers;

import java.util.List;
import java.util.Set;

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

import com.sarc.sarc.core.domain.entities.Resource;
import com.sarc.sarc.core.domain.entities.Room;
import com.sarc.sarc.core.services.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador para operações relacionadas a salas.
 * Apenas administradores podem manipular salas.
 */
@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Salas", description = "API para gerenciamento de salas")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @GetMapping
    @Operation(summary = "Listar todas as salas")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar sala por ID")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }
    
    @GetMapping("/capacity")
    @Operation(summary = "Buscar salas por capacidade mínima")
    public List<Room> getRoomsByMinCapacity(@RequestParam Integer capacity) {
        return roomService.getRoomsByMinCapacity(capacity);
    }
    
    @GetMapping("/building/{buildingId}/floor/{floor}")
    @Operation(summary = "Buscar salas por andar em um prédio específico")
    public List<Room> getRoomsByBuildingAndFloor(
            @PathVariable Long buildingId, 
            @PathVariable Integer floor) {
        return roomService.getRoomsByBuildingAndFloor(buildingId, floor);
    }
    
    @GetMapping("/resource/{resourceId}")
    @Operation(summary = "Buscar salas que possuem um determinado recurso")
    public List<Room> getRoomsByResource(@PathVariable Long resourceId) {
        return roomService.getRoomsByResource(resourceId);
    }
    
    @PostMapping("/building/{buildingId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar nova sala", description = "Apenas administradores podem criar salas")
    public Room createRoom(
            @PathVariable Long buildingId,
            @Validated @RequestBody Room room) {
        return roomService.createRoom(room, buildingId);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar sala", description = "Apenas administradores podem atualizar salas")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @Validated @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover sala", description = "Apenas administradores podem remover salas")
    public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
        if (roomService.deleteRoom(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{roomId}/resources")
    @Operation(summary = "Listar recursos de uma sala")
    public ResponseEntity<Set<Resource>> getRoomResources(@PathVariable Long roomId) {
        return roomService.getRoomResources(roomId);
    }
    
    @PostMapping("/{roomId}/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Adicionar recurso a uma sala", description = "Apenas administradores podem adicionar recursos")
    public ResponseEntity<Room> addResourceToRoom(
            @PathVariable Long roomId,
            @PathVariable Long resourceId) {
        return roomService.addResourceToRoom(roomId, resourceId);
    }
    
    @DeleteMapping("/{roomId}/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover recurso de uma sala", description = "Apenas administradores podem remover recursos")
    public ResponseEntity<Room> removeResourceFromRoom(
            @PathVariable Long roomId,
            @PathVariable Long resourceId) {
        return roomService.removeResourceFromRoom(roomId, resourceId);
    }
}
