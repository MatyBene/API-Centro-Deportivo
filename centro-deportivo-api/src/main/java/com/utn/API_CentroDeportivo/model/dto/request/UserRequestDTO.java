package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserRequestDTO {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String birthdate;
    private String phone;
    private String email;
}
