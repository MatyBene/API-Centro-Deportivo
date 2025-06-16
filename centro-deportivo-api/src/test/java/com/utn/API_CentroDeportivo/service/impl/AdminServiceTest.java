package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.AdminViewDTO;
import com.utn.API_CentroDeportivo.model.dto.response.UserDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.*;
import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.InvalidFilterCombinationException;
import com.utn.API_CentroDeportivo.model.exception.UserNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.model.validation.AdminValidation;
import com.utn.API_CentroDeportivo.model.validation.InstructorValidation;
import com.utn.API_CentroDeportivo.service.IAuthService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IAuthService authService;

    @Mock
    private ICredentialService credentialService;

    @Mock
    private ISportActivityService sportActivityService;

    @Mock
    private IEnrollmentService enrollmentService;

    @Mock
    private Validator validator;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AdminService adminService;

    private UserRequestDTO.UserRequestDTOBuilder userRequestBuilder;

    private Admin superAdmin, userManager;
    private Member member;
    private Instructor instructor;
    private final Long memberId = 1L;
    private final Long instructorId = 2L;
    private final Long superAdminId = 3L;
    private final Long userManagerId = 4L;
    private final String memberUsername = "testMember";

    @BeforeEach
    void setUp() {
        userRequestBuilder = UserRequestDTO.builder()
                .name("Test")
                .lastname("User")
                .dni("12345678")
                .birthdate("2000-01-01")
                .phone("123456")
                .email("test@mail.com")
                .username("testuser")
                .password("Password123!");

        member = new Member();
        member.setId(memberId);
        member.setStatus(Status.INACTIVE);
        Credential memberCredential = new Credential();
        memberCredential.setUsername(memberUsername);
        member.setCredential(memberCredential);

        instructor = new Instructor();
        instructor.setId(instructorId);

        superAdmin = new Admin();
        superAdmin.setId(superAdminId);
        superAdmin.setPermissionLevel(PermissionLevel.SUPER_ADMIN);

        userManager = new Admin();
        userManager.setId(userManagerId);
        userManager.setPermissionLevel(PermissionLevel.USER_MANAGER);
    }


}