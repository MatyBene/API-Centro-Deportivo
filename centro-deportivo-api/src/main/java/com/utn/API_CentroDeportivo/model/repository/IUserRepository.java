package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}
