package com.utn.API_CentroDeportivo.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class InstructorDetailsDTO extends UserDetailsDTO{
    private String specialty;
    private List<SportActivityDTO> activities;
}
