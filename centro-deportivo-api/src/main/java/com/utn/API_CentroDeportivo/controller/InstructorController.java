package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.InstructorSummaryDTO;
import com.utn.API_CentroDeportivo.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/instructors")

public class InstructorController {

    @Autowired
    private IInstructorService instructorService;

    @GetMapping("/{id}")
    public ResponseEntity<InstructorSummaryDTO> getInstructor(@PathVariable Long id){
        return instructorService.getInstructorSummaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
