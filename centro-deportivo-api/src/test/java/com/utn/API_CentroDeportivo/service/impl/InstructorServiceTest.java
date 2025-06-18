package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ICredentialService credentialService;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructor;
    private final Long instructorId = 1L;
    private final String instructorUsername = "usernameTest";
    private final String name = "nameTest";
    private final String lastname = "lastnameTest";
    private final String specialty = "specialtyTest";

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setId(instructorId);
        instructor.setName(name);
        instructor.setLastname(lastname);
        instructor.setSpecialty(specialty);
        instructor.setActivities(Collections.singletonList(new SportActivity()));
    }
}