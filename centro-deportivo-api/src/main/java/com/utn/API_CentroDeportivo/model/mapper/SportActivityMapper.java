package com.utn.API_CentroDeportivo.model.mapper;

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
}
