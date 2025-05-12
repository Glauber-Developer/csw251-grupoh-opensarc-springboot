package com.sarc.sarc.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarc.sarc.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
