package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;

public class SportActivityMapper {

    public static SportActivitySummaryDTO mapToSportActivitySummaryDTO(SportActivity activity) {
        SportActivitySummaryDTO activitySummaryDTO = new SportActivitySummaryDTO();
        activitySummaryDTO.setName(activity.getName());
        activitySummaryDTO.setMaxMembers(activity.getMaxMembers());
        activitySummaryDTO.setInstructorName(activity.getInstructor().getName());
        return activitySummaryDTO;
    }

    public static SportActivityDetailsDTO mapToSportActivityDetailsDTO(SportActivity activity) {
        SportActivityDetailsDTO activityDetailsDTO = new SportActivityDetailsDTO();
        activityDetailsDTO.setName(activity.getName());
        activityDetailsDTO.setMaxMembers(activity.getMaxMembers());
        activityDetailsDTO.setInstructorName(activity.getInstructor().getName());
        activityDetailsDTO.setDescription(activity.getDescription());
        activityDetailsDTO.setStartTime(String.valueOf(activity.getStartTime()));
        activityDetailsDTO.setEndTime(String.valueOf(activity.getEndTime()));
        activityDetailsDTO.setClassDays(activity.getClassDays());
        return activityDetailsDTO;
    }
}
