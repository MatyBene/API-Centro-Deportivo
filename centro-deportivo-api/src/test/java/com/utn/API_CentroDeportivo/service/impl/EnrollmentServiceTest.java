package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.repository.IEnrollmentRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private IEnrollmentRepository enrollmentRepository;

    @Mock
    private IMemberService memberService;

    @Mock
    private ICredentialService credentialService;

    @Mock
    private ISportActivityService sportActivityService;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Member member;
    private Instructor instructor;
    private SportActivity sportActivity;
    private Enrollment enrollment;
    private final String memberUsername = "testMember";
    private final String instructorUsername = "testInstructor";
    private final Long memberId = 1L;
    private final Status status = Status.INACTIVE;
    private final Long activityId = 1L;
    private final Integer maxMembers = 10;
    private final String activityName = "Yoga";
    private final Long instructorId = 1L;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setId(instructorId);

        member = new Member();
        member.setId(memberId);
        member.setStatus(status);

        sportActivity = new SportActivity();
        sportActivity.setId(activityId);
        sportActivity.setInstructor(instructor);
        sportActivity.setMaxMembers(maxMembers);
        sportActivity.setEnrollments(Collections.emptyList());
        sportActivity.setName(activityName);

        enrollment = Enrollment.builder()
                .id(1L)
                .member(member)
                .activity(sportActivity)
                .startDate(LocalDate.now())
                .build();
    }

    @Test
    void enrollMemberToActivity_WhenNotEnrolled_ShouldSucceed() {
        // Arrange
        when(credentialService.getUserByUsername(memberUsername)).thenReturn(member);
        when(sportActivityService.getSportActivityEntityById(activityId)).thenReturn(Optional.of(sportActivity));
        when(enrollmentRepository.findByMemberIdAndActivityId(memberId, activityId)).thenReturn(Optional.empty());

        // Act
        enrollmentService.enrollMemberToActivity(memberUsername, activityId);

        // Assert
        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));
        verify(memberService, times(1)).updateMemberStatus(memberId);
    }

    @Test
    void enrollMemberToActivity_WhenAlreadyEnrolled_ShouldThrowMemberAlreadyEnrolledException() {
        // Arrange
        when(enrollmentRepository.findByMemberIdAndActivityId(memberId, activityId)).thenReturn(Optional.of(enrollment));
        when(credentialService.getUserByUsername(memberUsername)).thenReturn(member);
        when(sportActivityService.getSportActivityEntityById(activityId)).thenReturn(Optional.of(sportActivity));

        // Act & Assert
        assertThrows(MemberAlreadyEnrolledException.class, () -> {
            enrollmentService.enrollMemberToActivity(memberUsername, activityId);
        });
        verify(enrollmentRepository, never()).save(any(Enrollment.class));
    }

}