package com.utn.API_CentroDeportivo.model.dto.response;

import com.utn.API_CentroDeportivo.model.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserDetailsDTO extends UserSummaryDTO{
    private String birthdate;
    private String phone;
    private String email;
    private String username;
    private Role role;
}
