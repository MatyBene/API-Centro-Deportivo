package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.CreateMemberDTO;
import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.mapper.CredentialMapper;
import com.utn.API_CentroDeportivo.model.mapper.MemberMapper;
import com.utn.API_CentroDeportivo.model.repository.ICredentialRepository;
import com.utn.API_CentroDeportivo.model.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private IMemberRepository memberRepository;

    @Transactional
    public void registerMember(CreateMemberDTO memberDTO){
        Member member = MemberMapper.mapToMember(memberDTO);
        Credential credential = CredentialMapper.mapToCredential(memberDTO.getCredentialDTO(), member);
        member.setCredential(credential);
        memberRepository.save(member);
    }

}
