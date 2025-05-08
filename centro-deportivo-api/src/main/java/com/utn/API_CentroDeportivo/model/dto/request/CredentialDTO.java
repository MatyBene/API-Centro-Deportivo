package com.utn.API_CentroDeportivo.model.dto.request;

import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CredentialDTO {
    private String username;
    private String password;
    private Role role = Role.MEMBER;
}
