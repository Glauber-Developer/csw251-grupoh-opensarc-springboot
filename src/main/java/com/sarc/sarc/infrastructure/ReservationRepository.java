package com.sarc.sarc.infrastructure;
import com.sarc.sarc.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{
    Optional<Reservation> findById(Long id);
}
