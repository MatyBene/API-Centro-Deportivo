package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IUserRepository userRepository;

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


}
