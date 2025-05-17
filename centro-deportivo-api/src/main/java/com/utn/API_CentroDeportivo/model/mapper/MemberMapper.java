package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.CreateMemberDTO;
import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;

public class MemberMapper {

    public static Member mapToMember(CreateMemberDTO memberDTO) {
        UserRequestDTO userDTO = memberDTO.getUserDTO();

        Member member = new Member();
        member.setName(userDTO.getName());
        member.setLastname(userDTO.getLastname());
        member.setDni(userDTO.getDni());
        member.setBirthdate(userDTO.getBirthdate());
        member.setPhone(userDTO.getPhone());
        member.setEmail(userDTO.getEmail());
        return member;
    }


}
