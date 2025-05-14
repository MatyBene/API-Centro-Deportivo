package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class CreateAdminDTO {
    private UserRequestDTO userDTO;
    private CredentialDTO credentialDTO;
}
