package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class EnrollmentRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long memberId;
    private Long activityId;
}
