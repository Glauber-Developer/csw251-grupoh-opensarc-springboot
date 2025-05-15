package com.sarc.sarc.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarc.sarc.core.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
}