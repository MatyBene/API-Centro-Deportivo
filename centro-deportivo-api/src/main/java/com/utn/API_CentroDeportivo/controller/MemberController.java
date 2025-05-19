package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollMemberInActivity(@RequestBody EnrollmentRequestDTO enrollmentDTO) {
        enrollmentService.enrollMemberToActivity(enrollmentDTO);
        memberService.updateMemberStatus(enrollmentDTO.getMemberId());
        return ResponseEntity.ok("El socio se inscribió correctamente en la actividad");
    }
}
