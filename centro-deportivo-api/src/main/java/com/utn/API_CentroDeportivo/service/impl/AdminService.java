package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.AdminViewDTO;
import com.utn.API_CentroDeportivo.model.entity.*;
import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.InvalidFilterCombinationException;
import com.utn.API_CentroDeportivo.model.exception.InvalidRoleException;
import com.utn.API_CentroDeportivo.model.mapper.AdminMapper;
import com.utn.API_CentroDeportivo.model.mapper.InstructorMapper;
import com.utn.API_CentroDeportivo.model.mapper.MemberMapper;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.model.validation.AdminValidation;
import com.utn.API_CentroDeportivo.model.validation.InstructorValidation;
import com.utn.API_CentroDeportivo.service.IAdminService;
import com.utn.API_CentroDeportivo.service.IAuthService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private Validator validator;

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void createUser(UserRequestDTO userDTO) {
        validate(userDTO);

        User user;
        switch (userDTO.getRole()) {
            case MEMBER:
                Member member = MemberMapper.mapToMember(userDTO);
                member.setStatus(Status.INACTIVE);
                user = member;
                break;
            case INSTRUCTOR:
                user = InstructorMapper.mapToInstructor(userDTO);
                break;
            case ADMIN:
                user = AdminMapper.mapToAdmin(userDTO);
                break;
            default:
                throw new InvalidRoleException("El rol especificado no es válido: " + userDTO.getRole());
        }

        Credential credential = Credential.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        user.setCredential(credential);

        authService.createAndSaveUser(user, userDTO.getRole());
    }

    @Override
    public Page<AdminViewDTO> getUsers(Role role, Status status, PermissionLevel permission, Pageable pageable) {
        Page<? extends User> userPage = userRepository.findUsersByFilters(role, status, permission, pageable);

        validateFilterCombination(role, status, permission);

        return userPage.map(user -> {
            if (user instanceof Admin admin) {
                return AdminMapper.toAdminViewDTO(admin);
            }
            if (user instanceof Instructor instructor) {
                return InstructorMapper.toAdminViewDTO(instructor);
            }
            if (user instanceof Member member) {
                return MemberMapper.toAdminViewDTO(member);
            }
            throw new IllegalArgumentException("Tipo de usuario desconocido: " + user.getClass());
        });
    }

    private void validateFilterCombination(Role role, Status status, PermissionLevel permission) {
        if (permission != null && role != Role.ADMIN) {
            throw new InvalidFilterCombinationException("El filtro 'permission' solo es aplicable para el rol 'ADMIN'.");
        }
        if (status != null && role != Role.MEMBER) {
            throw new InvalidFilterCombinationException("El filtro 'status' solo es aplicable para el rol 'MEMBER'.");
        }
    }

    private void validate(UserRequestDTO userDTO) {
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (userDTO.getRole() == Role.INSTRUCTOR) {
            Set<ConstraintViolation<UserRequestDTO>> instructorViolations = validator.validate(userDTO, InstructorValidation.class);
            if (!instructorViolations.isEmpty()) {
                throw new ConstraintViolationException(instructorViolations);
            }
        }

        if (userDTO.getRole() == Role.ADMIN) {
            Set<ConstraintViolation<UserRequestDTO>> adminViolations = validator.validate(userDTO, AdminValidation.class);
            if (!adminViolations.isEmpty()) {
                throw new ConstraintViolationException(adminViolations);
            }
        }
    }
}
