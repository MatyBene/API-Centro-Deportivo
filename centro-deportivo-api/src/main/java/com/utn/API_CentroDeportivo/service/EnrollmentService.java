package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;
import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.repository.IEnrollmentRepository;
import com.utn.API_CentroDeportivo.model.repository.ISportActivityRepository;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISportActivityRepository sportActivityRepository;

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Transactional
    public void enrollMemberToActivity(EnrollmentRequestDTO enrollmentDTO) {
        Long memberId = enrollmentDTO.getMemberId();
        Long activityId = enrollmentDTO.getActivityId();

        Member member = (Member) userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
        SportActivity activity = sportActivityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

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
