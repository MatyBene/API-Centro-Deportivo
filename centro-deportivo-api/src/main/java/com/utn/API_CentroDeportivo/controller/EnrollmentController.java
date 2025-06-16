package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.service.IAdminService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private ICredentialService credentialService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{activityId}/members/{memberId}")
    public ResponseEntity<String> removeMemberFromActivity(
            @PathVariable Long activityId,
            @PathVariable Long memberId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long instructorId = credentialService.getUserByUsername(username).getId();

        enrollmentService.cancelEnrollment(instructorId, activityId, memberId);
        return ResponseEntity.ok("El socio se dio de baja con éxito.");
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/my-activities/{activityId}/enroll/{memberId}")
    public ResponseEntity<String> enrollMemberToMyActivity(@PathVariable Long activityId, @PathVariable Long memberId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        enrollmentService.enrollMemberToActivityByInstructor(username, activityId, memberId);
        return ResponseEntity.ok("Socio inscripto correctamente a la actividad");
    }

}
