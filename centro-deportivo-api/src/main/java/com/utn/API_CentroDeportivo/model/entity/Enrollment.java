package com.utn.API_CentroDeportivo.model.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Enrollment {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Member member;
    private SportActivity activity;
}
