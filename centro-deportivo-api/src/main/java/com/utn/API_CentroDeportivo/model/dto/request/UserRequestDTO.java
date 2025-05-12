package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
