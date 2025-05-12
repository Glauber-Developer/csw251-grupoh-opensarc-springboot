package com.sarc.sarc.app.controller.controllers;

import com.sarc.sarc.domain.Resource;
import com.sarc.sarc.domain.Room;
import com.sarc.sarc.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Controlador para operações relacionadas a salas.
 * Apenas administradores podem manipular salas.
 */
@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Salas", description = "API para gerenciamento de salas")
public class RoomController {
    
    private final RoomService roomService;
    
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todas as salas")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar sala por ID")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/capacity")
    @Operation(summary = "Buscar salas por capacidade mínima")
    public ResponseEntity<List<Room>> getRoomsByMinCapacity(@RequestParam Integer capacity) {
        return ResponseEntity.ok(roomService.getRoomsByMinCapacity(capacity));
    }
    
    @GetMapping("/building/{buildingId}/floor/{floor}")
    @Operation(summary = "Buscar salas por andar em um prédio específico")
    public ResponseEntity<List<Room>> getRoomsByBuildingAndFloor(
            @PathVariable Long buildingId, 
            @PathVariable Integer floor) {
        return ResponseEntity.ok(roomService.getRoomsByBuildingAndFloor(buildingId, floor));
    }
    
    @GetMapping("/resource/{resourceId}")
    @Operation(summary = "Buscar salas que possuem um determinado recurso")
    public ResponseEntity<List<Room>> getRoomsByResource(@PathVariable Long resourceId) {
        return ResponseEntity.ok(roomService.getRoomsByResource(resourceId));
    }
    
    @PostMapping("/building/{buildingId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar nova sala", description = "Apenas administradores podem criar salas")
    public ResponseEntity<Room> createRoom(
            @PathVariable Long buildingId,
            @Valid @RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.createRoom(room, buildingId));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar sala", description = "Apenas administradores podem atualizar salas")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room room) {
        return roomService.updateRoom(id, room)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover sala", description = "Apenas administradores podem remover salas")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        if (roomService.deleteRoom(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{roomId}/resources")
    @Operation(summary = "Listar recursos de uma sala")
    public ResponseEntity<Set<Resource>> getRoomResources(@PathVariable Long roomId) {
        return roomService.getRoomResources(roomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{roomId}/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Adicionar recurso a uma sala", description = "Apenas administradores podem adicionar recursos")
    public ResponseEntity<Room> addResourceToRoom(
            @PathVariable Long roomId,
            @PathVariable Long resourceId) {
        return roomService.addResourceToRoom(roomId, resourceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{roomId}/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remover recurso de uma sala", description = "Apenas administradores podem remover recursos")
    public ResponseEntity<Room> removeResourceFromRoom(
            @PathVariable Long roomId,
            @PathVariable Long resourceId) {
        return roomService.removeResourceFromRoom(roomId, resourceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
