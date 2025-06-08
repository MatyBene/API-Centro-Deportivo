package com.utn.API_CentroDeportivo.model.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class InstructorSummaryDTO extends UserSummaryDTO{
    private String specialty;
    private int activityCount;
}
