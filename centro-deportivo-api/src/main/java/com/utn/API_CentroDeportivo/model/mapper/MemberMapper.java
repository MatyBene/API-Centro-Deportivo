package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;

public class MemberMapper {

    public static Member mapToMember(MemberRequestDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setLastname(memberDTO.getLastname());
        member.setDni(memberDTO.getDni());
        member.setBirthdate(memberDTO.getBirthdate());
        member.setPhone(memberDTO.getPhone());
        member.setEmail(memberDTO.getEmail());
        return member;
    }

    public static Member mapToMember(UserRequestDTO dto) {
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
