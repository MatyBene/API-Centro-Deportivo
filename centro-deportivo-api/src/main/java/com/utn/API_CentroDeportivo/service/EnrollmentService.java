package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.repository.IEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ISportActivityService sportActivityService;

    @Transactional
    public void enrollMemberToActivity(EnrollmentRequestDTO enrollmentDTO) {
        Long memberId = enrollmentDTO.getMemberId();
        Long activityId = enrollmentDTO.getActivityId();

        Member member = memberService.getMemberById(memberId).get();
        SportActivity activity = sportActivityService.getSportActivityById(activityId).get();

        if (enrollmentRepository.findByMemberIdAndActivityId(memberId, activityId).isPresent()) {
            throw new MemberAlreadyEnrolledException("El socio ya está inscripto en esta actividad");
        }

        Enrollment enrollment = Enrollment.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .member(member)
                .activity(activity)
                .build();

        enrollmentRepository.save(enrollment);
    }
}
