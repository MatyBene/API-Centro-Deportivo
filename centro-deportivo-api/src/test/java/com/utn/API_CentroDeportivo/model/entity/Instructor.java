package com.utn.API_CentroDeportivo.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Instructor {
    private Specialty specialty;
    private List<FitClass> classes;
}
