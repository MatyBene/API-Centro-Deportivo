package com.utn.API_CentroDeportivo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICredentialService extends UserDetailsService {
    boolean existsByUsername(String username);
}
