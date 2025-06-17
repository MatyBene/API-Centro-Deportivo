package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.config.SecurityConfig;
import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ICredentialService credentialService;

    @Mock
    private SecurityConfig securityConfig;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private MemberRequestDTO memberRequestDTO;
    private Member member;

    @BeforeEach
    void setUp() {
        memberRequestDTO = MemberRequestDTO.builder()
                .name("Max")
                .lastname("Verstappen")
                .dni("123456789")
                .birthdate("1800-09-06")
                .phone("0123456789")
                .email("mv@hotmail.com")
                .username("maxv1")
                .password("Password123!")
                .build();

        member = new Member();
        member.setName(memberRequestDTO.getName());
        member.setDni(memberRequestDTO.getDni());
        member.setEmail(memberRequestDTO.getEmail());
        Credential credential = new Credential();
        credential.setUsername(memberRequestDTO.getUsername());
        credential.setPassword(memberRequestDTO.getPassword());
        member.setCredential(credential);

        when(securityConfig.passwordEncoder()).thenReturn(passwordEncoder);
    }



}