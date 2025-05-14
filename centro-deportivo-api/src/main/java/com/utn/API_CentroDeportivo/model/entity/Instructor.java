package com.utn.API_CentroDeportivo.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class Instructor extends User{
    private String specialty;
    private List<SportActivity> classes;
}
