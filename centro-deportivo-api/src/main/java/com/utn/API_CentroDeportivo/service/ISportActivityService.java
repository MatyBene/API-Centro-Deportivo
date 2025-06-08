package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;

import java.util.List;
import java.util.Optional;

public interface ISportActivityService {
    List<SportActivitySummaryDTO> getActivities();
    Optional<SportActivityDetailsDTO> getActivityById(Long id);
    int getCurrentMembers(Long id);
    List<SportActivitySummaryDTO> getActivitiesByInstructor(Instructor instructor);
    List<SportActivityDetailsDTO> getActivitiesDetailsByInstructor(Instructor instructor);
    Optional<SportActivity> getSportActivityById(Long id);
}
