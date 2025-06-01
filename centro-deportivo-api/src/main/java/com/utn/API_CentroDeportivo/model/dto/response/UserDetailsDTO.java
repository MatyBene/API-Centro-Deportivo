package com.utn.API_CentroDeportivo.model.dto.response;

import com.utn.API_CentroDeportivo.model.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class UserDetailsDTO extends UserSummaryDTO{
    private String birthdate;
    private String phone;
    private String email;
    private String dni;
    private String username;
    private Role role;
}
