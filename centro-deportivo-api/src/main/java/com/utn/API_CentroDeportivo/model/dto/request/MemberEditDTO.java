package com.utn.API_CentroDeportivo.model.dto.request;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MemberEditDTO {
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String birthdate;
}
