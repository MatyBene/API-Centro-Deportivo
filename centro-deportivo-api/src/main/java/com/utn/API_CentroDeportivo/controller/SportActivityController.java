package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities")
public class SportActivityController {

    @Autowired
    private ISportActivityService sportActivityService;

    @GetMapping("/search")
    public ResponseEntity<Page<SportActivitySummaryDTO>> searchActivitiesByName(@RequestParam String name,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.findActivitiesByName(name, pageable);
        return ResponseEntity.ok(activities);
    }
}
