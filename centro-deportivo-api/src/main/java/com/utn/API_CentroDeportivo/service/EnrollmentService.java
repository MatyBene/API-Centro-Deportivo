package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.repository.IEnrollmentRepository;
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

    @Transactional
    public void enrollMemberToActivity(String username, Long activityId) {

        Member member = (Member) credentialService.getUserByUsername(username);
        SportActivity activity = sportActivityService.getSportActivityEntityById(activityId).get();

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
}
