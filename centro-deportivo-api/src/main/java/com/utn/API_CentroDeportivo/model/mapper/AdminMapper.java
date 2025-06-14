package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;

public class AdminMapper {

    public static Member mapToAdmin(UserRequestDTO dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setLastname(dto.getLastname());
        member.setDni(dto.getDni());
        member.setBirthdate(dto.getBirthdate());
        member.setPhone(dto.getPhone());
        member.setEmail(dto.getEmail());
        return member;
    }
}
