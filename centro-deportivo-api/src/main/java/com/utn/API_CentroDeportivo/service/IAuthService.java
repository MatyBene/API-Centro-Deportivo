package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.CreateMemberDTO;

public interface IAuthService {
    void registerMember(CreateMemberDTO memberDTO);
}
