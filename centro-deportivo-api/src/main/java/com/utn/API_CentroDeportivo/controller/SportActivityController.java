package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1/activities")
public class SportActivityController {

    @Autowired
    private ISportActivityService sportActivityService;

    @GetMapping()
    public ResponseEntity<Page<SportActivitySummaryDTO>> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.getActivities(pageable);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportActivityDetailsDTO> getActivity(@PathVariable Long id) {
        return sportActivityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SportActivitySummaryDTO>> searchActivitiesByName(@RequestParam String name,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.findActivitiesByName(name, pageable);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/search-by-time")
    public ResponseEntity<Page<SportActivitySummaryDTO>> searchActivitiesByTimeRange(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.findActivitiesByTimeRange(startTime, endTime, pageable);
        return ResponseEntity.ok(activities);
    }
}
