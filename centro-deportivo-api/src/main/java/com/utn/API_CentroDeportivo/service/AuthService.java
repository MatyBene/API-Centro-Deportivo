package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.CredentialRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.FieldAlreadyExistsException;
import com.utn.API_CentroDeportivo.model.mapper.CredentialMapper;
import com.utn.API_CentroDeportivo.model.mapper.MemberMapper;
import com.utn.API_CentroDeportivo.model.repository.ICredentialRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICredentialRepository credentialRepository;

    @Transactional
    public void registerMember(UserRequestDTO memberDTO) {
        if (userRepository.existsByDni(memberDTO.getDni())){
            throw new FieldAlreadyExistsException("El DNI ya está registrado");
        }
        if (userRepository.existsByEmail(memberDTO.getEmail())) {
            throw new FieldAlreadyExistsException("El email ya está registrado");
        }
        if (credentialRepository.existsByUsername(memberDTO.getUsername())) {
            throw new FieldAlreadyExistsException("El nombre de usuario ya está registrado");
        }
        Member member = MemberMapper.mapToMember(memberDTO);
        CredentialRequestDTO credentialDTO = CredentialRequestDTO.builder().username(memberDTO.getUsername()).password(memberDTO.getPassword()).build();
        Credential credential = CredentialMapper.mapToCredential(credentialDTO, member);
        credential.setRole(Role.MEMBER);
        member.setCredential(credential);
        member.setStatus(Status.INACTIVE);
        userRepository.save(member);
    }

}
