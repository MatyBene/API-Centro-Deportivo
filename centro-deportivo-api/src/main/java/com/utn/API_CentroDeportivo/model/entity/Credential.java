package com.utn.API_CentroDeportivo.model.entity;
import com.utn.API_CentroDeportivo.model.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Credential {
    private long id;
    private User user;
    private String username;
    private String password;
    private Role role;
}
