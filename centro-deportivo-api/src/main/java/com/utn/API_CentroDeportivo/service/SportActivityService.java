package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.SportActivityNotFoundException;
import com.utn.API_CentroDeportivo.model.mapper.SportActivityMapper;
import com.utn.API_CentroDeportivo.model.repository.ISportActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.utn.API_CentroDeportivo.model.mapper.SportActivityMapper.mapToSportActivitySummaryDTO;

@Service
public class SportActivityService implements ISportActivityService{
    @Autowired
    private ISportActivityRepository sportActivityRepository;

    public List<SportActivitySummaryDTO> getActivities() {
        return sportActivityRepository.findAll().stream().map(SportActivityMapper::mapToSportActivitySummaryDTO).toList();
    }

    public Optional<SportActivityDetailsDTO> getActivityById(Long id) {
        Optional<SportActivity> activity = sportActivityRepository.findById(id);
        if (activity.isPresent()) {
            SportActivityDetailsDTO activityDetailsDTO = SportActivityMapper.mapToSportActivityDetailsDTO(activity.get());
            activityDetailsDTO.setCurrentMembers(getCurrentMembers(id));
            return Optional.of(activityDetailsDTO);
        }
        return Optional.empty();
    }

    public int getCurrentMembers(Long id) {
        return sportActivityRepository.findById(id).map(activity -> activity.getEnrollments() != null ? activity.getEnrollments().size() : 0).orElse(0);
    }

    @Override
    public List<SportActivitySummaryDTO> getActivitiesByInstructor(Instructor instructor) {
        List<SportActivity> activities = sportActivityRepository.findByInstructor(instructor);
        return activities.stream().map(SportActivityMapper::mapToSportActivitySummaryDTO).toList();
    }

    @Override
    public List<SportActivityDetailsDTO> getActivitiesDetailsByInstructor(Instructor instructor) {
        List<SportActivity> activities = sportActivityRepository.findByInstructor(instructor);
        return activities.stream()
                .map(SportActivityMapper::mapToSportActivityDetailsDTO)
                .toList();
    }
}