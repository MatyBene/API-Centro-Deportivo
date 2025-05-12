package com.utn.API_CentroDeportivo.model.dto.request;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InstructorRequestDTO {
    private String specialty;
    private List<SportActivitySummaryDTO> activities;
}
