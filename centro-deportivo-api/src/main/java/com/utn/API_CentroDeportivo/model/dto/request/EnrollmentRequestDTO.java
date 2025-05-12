package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EnrollmentRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long memberId;
    private Long activityId;
}
