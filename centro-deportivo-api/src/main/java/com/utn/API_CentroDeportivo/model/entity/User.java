package com.utn.API_CentroDeportivo.model.entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class User {
    private long id;
    private String name;
    private String lastname;
    private String dni;
    private String birthdate;
    private String phone;
    private String email;
}
