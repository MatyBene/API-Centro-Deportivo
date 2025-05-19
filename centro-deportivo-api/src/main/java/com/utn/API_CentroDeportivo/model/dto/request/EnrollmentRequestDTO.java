package com.utn.API_CentroDeportivo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El is del socio es obligatorio")
    private Long memberId;

    @NotBlank(message = "El id de la actividad es obligatorio")
    private Long activityId;
}
