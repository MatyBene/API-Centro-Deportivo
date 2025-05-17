package com.utn.API_CentroDeportivo.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class CreateMemberDTO {
    @NotNull(message = "Los datos del usuario no pueden ser nulos")
    private UserRequestDTO userDTO;
    @NotNull(message = "Los datos del socio no pueden ser nulos")
    private MemberRequestDTO memberDTO;
    @NotNull(message = "Los datos de la credencial no pueden ser nulos")
    private CredentialDTO credentialDTO;
}
