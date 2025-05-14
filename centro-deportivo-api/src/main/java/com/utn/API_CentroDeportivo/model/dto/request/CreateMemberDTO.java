package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class CreateMemberDTO {
    private UserRequestDTO userDTO;
    private MemberRequestDTO memberDTO;
    private CredentialDTO credentialDTO;
}
