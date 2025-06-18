package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.request.MemberEditDTO;
import com.utn.API_CentroDeportivo.model.dto.response.MembersDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.Credential;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.IMemberRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IMemberRepository memberRepository;

    @Mock
    private ICredentialService credentialService;

    @InjectMocks
    private MemberService memberService;

    private Member member;
    private User userForCredential;
    private MemberEditDTO memberEditDTO;
    private final Long memberId = 1L;
    private final String memberUsername = "usernameTest";
    private final String nameOriginal = "Original Name";
    private final String lastnameOriginal = "Original Lastname";
    private final Status status = Status.INACTIVE;

    @BeforeEach
    void setUp() {
        Credential credential = new Credential();
        credential.setUsername(memberUsername);

        member = new Member();
        member.setId(memberId);
        member.setName(nameOriginal);
        member.setLastname(lastnameOriginal);
        member.setStatus(status);
        member.setCredential(credential);
        member.setEnrollments(Collections.emptyList());

        memberEditDTO = new MemberEditDTO("New Name", "New Lastname", "123456789", "new@email.com", "2000-01-01");

        userForCredential = new Member();
        userForCredential.setId(memberId);
    }

}