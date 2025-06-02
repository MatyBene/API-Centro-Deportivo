package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.entity.Member;

public interface IMemberService {
    void updateMemberStatus(Long memberId);
    Member getMemberById(Long memberId);
}
