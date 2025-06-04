package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.repository.ICredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CredentialService implements ICredentialService{

    @Autowired
    private ICredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = credentialRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return user;
    }
}
