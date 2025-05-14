package com.utn.API_CentroDeportivo.model.dto.request;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class InstructorRequestDTO {
    private String specialty;
    private List<SportActivitySummaryDTO> activities;
}
