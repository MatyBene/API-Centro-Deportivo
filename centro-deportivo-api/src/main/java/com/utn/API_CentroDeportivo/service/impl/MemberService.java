package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.request.MemberEditDTO;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.IMemberRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private ICredentialService credentialService;

    @Transactional
    public void updateMemberStatus(Long memberId) {
        Member existingMember = (Member) userRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Socio no encontrado"));

        existingMember.setStatus(Status.ACTIVE);

        userRepository.save(existingMember);
    }

    @Override
    public Optional<Member> getMemberById(Long memberId) {
        return Optional.ofNullable((Member) userRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Socio no encontrado")));
    }

    @Override
    public void saveMember(User member) {
        userRepository.save(member);
    }

    @Transactional
    @Override
    public void updateMemberProfile(String username, MemberEditDTO dto) {
        Member member = (Member) userRepository.findById(credentialService.getUserByUsername(username).getId())
                .orElseThrow(( ) -> new MemberNotFoundException("Socio no encontrado"));

        if (dto.getName() != null) {
            member.setName(dto.getName());
        }
        if (dto.getLastname() != null) {
            member.setLastname(dto.getLastname());
        }

        if (dto.getPhone() != null) {
            member.setPhone(dto.getPhone());
        }

        if (dto.getEmail() != null) {
            member.setEmail(dto.getEmail());
        }
        if (dto.getBirthdate() != null) {
            member.setBirthdate(dto.getBirthdate());
        }
        userRepository.save(member);
    }
    @Transactional
    @Override
    public void deleteMemberByUsername(String username) {
        Member member = (Member) userRepository.findById(credentialService.getUserByUsername(username).getId())
                .orElseThrow(() -> new MemberNotFoundException("Socio no encontrado"));
        userRepository.delete(member);
    }


}
