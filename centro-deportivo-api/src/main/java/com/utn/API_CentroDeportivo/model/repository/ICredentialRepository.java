package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ICredentialRepository extends JpaRepository<Credential, Long> {

    boolean existsByUsername(String username);
    UserDetails findByUsername(String username);
}
