package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.config.SecurityConfig;
import com.utn.API_CentroDeportivo.model.dto.request.CredentialRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.FieldAlreadyExistsException;
import com.utn.API_CentroDeportivo.model.mapper.CredentialMapper;
import com.utn.API_CentroDeportivo.model.mapper.MemberMapper;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.IAuthService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IInstructorService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IInstructorService instructorService;

//    @Autowired
//    private IAdminService adminService;

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private SecurityConfig securityConfig;

    @Transactional
    public void registerMember(MemberRequestDTO memberDTO) {
        validateUserFields(memberDTO);
        Member member = MemberMapper.mapToMember(memberDTO);
        member.setStatus(Status.INACTIVE);
        Credential credential = Credential.builder().username(memberDTO.getUsername()).password(memberDTO.getPassword()).build();
        member.setCredential(credential);
        createAndSaveUser(member, Role.MEMBER);
    }

    public void createAndSaveUser(User user, Role role) {
        CredentialRequestDTO credentialDTO = CredentialRequestDTO.builder()
                .username(user.getCredential().getUsername())
                .password(securityConfig.passwordEncoder().encode(user.getCredential().getPassword()))
                .build();
        Credential credential = CredentialMapper.mapToCredential(credentialDTO, user);
        credential.setRole(role);
        user.setCredential(credential);
        memberService.saveMember(user);
    }

    private void validateUserFields(MemberRequestDTO userDTO) {
        if (userRepository.existsByDni(userDTO.getDni())) {
            throw new FieldAlreadyExistsException("dni", "El campo ya está registrado");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new FieldAlreadyExistsException("email", "El campo ya está registrado");
        }
        if (credentialService.existsByUsername(userDTO.getUsername())) {
            throw new FieldAlreadyExistsException("username", "El campo ya está registrado");
        }
    }

}
