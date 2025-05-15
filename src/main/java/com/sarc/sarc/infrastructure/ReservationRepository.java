package com.sarc.sarc.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.core.domain.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    
    @Override
    Optional<Reservation> findById(Long id);
}