package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;
    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setId(memberId);
        member.setStatus(Status.INACTIVE);
    }

    @Test
    void updateMemberStatus_SuccessfulUpdate_WhenMemberExists() {
        // Arrange
        when(userRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(userRepository.save(any(Member.class))).thenReturn(member);

        // Act
        memberService.updateMemberStatus(memberId);

        // Assert
        assertEquals(Status.ACTIVE, member.getStatus());
        verify(userRepository, times(1)).findById(memberId);
        verify(userRepository, times(1)).save(member);
    }

    @Test
    void updateMemberStatus_ThrowsException_WhenMemberNotFound(){
        // Arrange
        when(userRepository.findById(memberId)).thenReturn(Optional.empty());

        // Act & Assert
        MemberNotFoundException exception = assertThrows(MemberNotFoundException.class, () -> {
            memberService.updateMemberStatus(memberId);
        });
        assertEquals("Socio no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findById(memberId);
        verify(userRepository, never()).save(any(Member.class));
    }
}