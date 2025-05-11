package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateMemberDTO {

    private UserRequestDTO userDTO;
    private MemberRequestDTO memberDTO;
    private CredentialDTO credentialDTO;
}
