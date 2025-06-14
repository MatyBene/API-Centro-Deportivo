package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;

public interface IAdminService {
    void createUser(MemberRequestDTO userDTO);
}
