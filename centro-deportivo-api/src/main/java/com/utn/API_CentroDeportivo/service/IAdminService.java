package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.AdminViewDTO;
import com.utn.API_CentroDeportivo.model.dto.response.UserDetailsDTO;
import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAdminService {
    void createUser(UserRequestDTO userDTO);
    Page<AdminViewDTO> getUsers(Role role, Status status, PermissionLevel permission, Pageable pageable);
    Optional<UserDetailsDTO> findUserDetailsByUsername(String username);
    void deleteUserById(Long id);
    void deleteUserByUsername(String username);
}
