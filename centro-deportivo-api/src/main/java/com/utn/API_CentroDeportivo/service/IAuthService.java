package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;

public interface IAuthService {
    void registerMember(UserRequestDTO memberDTO);
}
