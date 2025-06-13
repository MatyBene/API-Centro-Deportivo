package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.MemberEditDTO;
import com.utn.API_CentroDeportivo.model.dto.response.EnrollmentDTO;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollMemberInActivity(@RequestBody EnrollmentRequestDTO enrollmentDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        enrollmentService.enrollMemberToActivity(username, enrollmentDTO.getActivityId());

        return ResponseEntity.ok("El socio se inscribió correctamente en la actividad");
    }

    @PreAuthorize("hasRole('MEMBER')")
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteOwnAccount(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        memberService.deleteMemberByUsername(username);

        return ResponseEntity.ok("Cuenta eliminada correctamente");
    }

    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody MemberEditDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        memberService.updateMemberProfile(username, dto);
        return ResponseEntity.ok("Se modifico el usuario correctamente");
    }

    @PreAuthorize("hasRole('MEMBER')")
    @DeleteMapping("/activities/{activityId}")
    public ResponseEntity<String> unsubscribeFromActivity(@PathVariable Long activityId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        enrollmentService.unsubscribeMemberFromActivity(username, activityId);
        return ResponseEntity.ok("Te diste de baja de la actividad con éxito");
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/activities")
    public ResponseEntity<List<EnrollmentDTO>> getMyActivities() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByUsername(username);
        return ResponseEntity.ok(enrollments);
    }
}
