package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.repository.IEnrollmentRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private ISportActivityService sportActivityService;

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public void enrollMemberToActivity(String username, Long activityId) {

        Member member = (Member) credentialService.getUserByUsername(username);
        SportActivity activity = sportActivityService.getSportActivityById(activityId).get();

        if (enrollmentRepository.findByMemberIdAndActivityId(member.getId(), activityId).isPresent()) {
            throw new MemberAlreadyEnrolledException("El socio ya está inscripto en esta actividad");
        }

        Enrollment enrollment = Enrollment.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .member(member)
                .activity(activity)
                .build();

        enrollmentRepository.save(enrollment);
        memberService.updateMemberStatus(member.getId());
    }
    @Transactional
    @Override
    public void unsubscribeMemberFromActivity(String username, Long activityId) {
        Member member = (Member) credentialService.getUserByUsername(username);

        Enrollment enrollment = enrollmentRepository.findByMemberIdAndActivityId(member.getId(), activityId)
                .orElseThrow(() -> new IllegalStateException("El socio no está inscripto en esta actividad"));

        enrollmentRepository.delete(enrollment);

        boolean hasOtherEnrollments = enrollmentRepository.existsByMemberId(member.getId());
        if (!hasOtherEnrollments) {
            member.setStatus(Status.INACTIVE);
            userRepository.save(member);
        }
    }
}
