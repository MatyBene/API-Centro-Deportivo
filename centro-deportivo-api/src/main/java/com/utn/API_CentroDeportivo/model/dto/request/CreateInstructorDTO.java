package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateInstructorDTO {
    private UserRequestDTO userDTO;
    private InstructorRequestDTO instructorDTO;
    private CredentialDTO credentialDTO;
}
