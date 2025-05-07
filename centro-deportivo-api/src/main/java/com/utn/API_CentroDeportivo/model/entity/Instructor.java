package com.utn.API_CentroDeportivo.model.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Instructor {
    private String specialty;
    private List<SportActivity> classes;
}
