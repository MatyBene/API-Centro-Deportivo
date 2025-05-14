package com.utn.API_CentroDeportivo.model.entity;
import com.utn.API_CentroDeportivo.model.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class Credential {
    private Long id;
    private User user;
    private String username;
    private String password;
    private Role role;
}
