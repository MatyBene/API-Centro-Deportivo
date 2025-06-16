package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Admin;

import java.time.LocalDate;

public class AdminMapper {

    public static Admin mapToAdmin(UserRequestDTO dto) {
        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setLastname(dto.getLastname());
        admin.setDni(dto.getDni());
        admin.setBirthdate(dto.getBirthdate());
        admin.setPhone(dto.getPhone());
        admin.setEmail(dto.getEmail());
        admin.setPermissionLevel(dto.getPermissionLevel());
        admin.setHireDate(LocalDate.now());
        return admin;
    }
}
