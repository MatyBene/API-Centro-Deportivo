package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Role;

public interface IAuthService {
    void registerMember(UserRequestDTO memberDTO);
    void createAndSaveUser(User user, Role role);
}
