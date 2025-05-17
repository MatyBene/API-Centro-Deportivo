package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.service.SportActivityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/public")

public class PublicController {
    @Autowired
    private SportActivityService sportActivityService;

    @GetMapping ("/activities")
    public ResponseEntity<List<SportActivity>> getActivities() {
        List<SportActivity> activities = sportActivityService.getActivities();
        return ResponseEntity.ok(activities);
}


    @GetMapping("/activities/{id}")
    public ResponseEntity<SportActivity> getActivity(@PathVariable Long id) {
        return sportActivityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
