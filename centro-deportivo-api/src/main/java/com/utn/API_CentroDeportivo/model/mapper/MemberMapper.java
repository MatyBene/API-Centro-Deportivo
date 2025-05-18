package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;

public class MemberMapper {

    public static Member mapToMember(UserRequestDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setLastname(memberDTO.getLastname());
        member.setDni(memberDTO.getDni());
        member.setBirthdate(memberDTO.getBirthdate());
        member.setPhone(memberDTO.getPhone());
        member.setEmail(memberDTO.getEmail());
        return member;
    }


}
