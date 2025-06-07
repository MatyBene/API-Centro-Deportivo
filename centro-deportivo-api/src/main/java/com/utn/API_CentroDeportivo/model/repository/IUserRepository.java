package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
}
