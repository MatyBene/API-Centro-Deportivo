package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.InstructorSummaryDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.service.IInstructorService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")

public class InstructorController {

    @Autowired
    private IInstructorService instructorService;

    @Autowired
    private ISportActivityService sportActivityService;

    @GetMapping("/{id}")
    public ResponseEntity<InstructorSummaryDTO> getInstructor(@PathVariable Long id){
        return instructorService.getInstructorSummaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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

}
