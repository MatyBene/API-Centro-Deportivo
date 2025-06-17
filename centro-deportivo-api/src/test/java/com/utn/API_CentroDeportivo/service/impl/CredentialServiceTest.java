package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.repository.ICredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CredentialServiceTest {

    @Mock
    private ICredentialRepository credentialRepository;

    @InjectMocks
    private CredentialService credentialService;

    private Credential credential;
    private User user;
    private final String existingUsername = "existingUser";
    private final String nonExistentUsername = "nonExistentUser";
    private final Long userId = 1L;
    private final Long credentialId = 1L;
    private final String name = "Matias";
    private final String password = "Password123!";

    @BeforeEach
    void setUp() {
        user = new Member();
        user.setId(userId);
        user.setName(name);

        credential = new Credential();
        credential.setId(credentialId);
        credential.setUsername(existingUsername);
        credential.setPassword(password);
        credential.setUser(user);
    }
}