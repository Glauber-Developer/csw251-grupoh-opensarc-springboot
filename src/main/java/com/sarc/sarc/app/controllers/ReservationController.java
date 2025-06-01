package com.sarc.sarc.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sarc.sarc.core.domain.entities.Reservation;
import com.sarc.sarc.core.services.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador para operações relacionadas a reservas.
 */
@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservas", description = "API para gerenciamento de reservas")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @Operation(summary = "Listar todas as reservas")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por ID")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    @Operation(summary = "Criar nova reserva")
    public Reservation createReservation(@Validated @RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar reserva existente")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @Validated @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover reserva por ID")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservation(id) ? 
            ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}