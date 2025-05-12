package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateAdminDTO {
    private UserRequestDTO userDTO;
    private CredentialDTO credentialDTO;
}
