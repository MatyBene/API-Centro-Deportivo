package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.EnrollmentDTO;
import com.utn.API_CentroDeportivo.model.dto.response.MembersDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;

import java.util.stream.Collectors;

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
    public static MembersDetailsDTO mapToMemberDetailsDTO(Member member) {
        return MembersDetailsDTO.builder()
                .name(member.getName())
                .lastname(member.getLastname())
                .dni(member.getDni())
                .birthdate(member.getBirthdate())
                .phone(member.getPhone())
                .email(member.getEmail())
                .username(member.getCredential().getUsername())
                .role(member.getCredential().getRole())
                .status(member.getStatus())
                .enrollments(member.getEnrollments().stream()
                        .map(enrollment -> EnrollmentDTO.builder()
                                .activityName(enrollment.getActivity().getName())
                                .startDate(enrollment.getStartDate())
                                .endDate(enrollment.getEndDate())
                                .build())
                        .collect(Collectors.toList()))
                .build();
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
