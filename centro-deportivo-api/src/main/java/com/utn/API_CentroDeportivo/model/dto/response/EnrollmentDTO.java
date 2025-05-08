package com.utn.API_CentroDeportivo.model.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EnrollmentDTO {
    private String activityName;
    private LocalDate startDate;
    private LocalDate endDate;
}
