package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.entity.Member;

import java.util.Optional;

public interface IMemberService {
    void updateMemberStatus(Long memberId);
    Optional<Member> getMemberById(Long memberId);
}
