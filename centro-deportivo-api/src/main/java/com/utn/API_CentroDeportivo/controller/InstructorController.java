package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.InstructorSummaryDTO;
import com.utn.API_CentroDeportivo.model.dto.response.MembersDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.service.IAuthService;
import com.utn.API_CentroDeportivo.service.IInstructorService;
import com.utn.API_CentroDeportivo.service.IMemberService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/instructors")

public class InstructorController {

    @Autowired
    private IInstructorService instructorService;

    @Autowired
    private ISportActivityService sportActivityService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IAuthService authService;


    @GetMapping("/{id}")
    public ResponseEntity<InstructorSummaryDTO> getInstructor(@PathVariable Long id) {
        return instructorService.getInstructorSummaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/my-activities")
    public ResponseEntity<List<SportActivityDetailsDTO>> getMyActivities() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return instructorService.findByUsername(username)
                .map(instructor -> {
                    List<SportActivityDetailsDTO> activities = sportActivityService.getActivitiesDetailsByInstructor(instructor);
                    return ResponseEntity.ok(activities);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/my-activities/{activityId}")
    public ResponseEntity<SportActivityDetailsDTO> getMyActivityDetails(@PathVariable Long activityId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Instructor> optionalInstructor = instructorService.findByUsername(username);
        Optional<SportActivityDetailsDTO> optionalActivity = sportActivityService.getActivityById(activityId);

        if (optionalInstructor.isPresent() && optionalActivity.isPresent()) {
            Instructor instructor = optionalInstructor.get();
            SportActivityDetailsDTO activity = optionalActivity.get();

            if (activity.getInstructorName().equals(instructor.getName())) { // o getFullName()
                return ResponseEntity.ok(activity);
            }
        }

        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/members")
    public ResponseEntity<Page<MembersDetailsDTO>> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(memberService.getAllMembers(page, size));
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MembersDetailsDTO> getMemberDetails(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberDetailsById(memberId));
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/register-member")
    public ResponseEntity<String> registerMemberByInstructor(
            @Valid @RequestBody MemberRequestDTO memberDTO) {

        authService.registerMember(memberDTO);
        return ResponseEntity.ok("Socio registrado correctamente por el instructor");
    }
}
