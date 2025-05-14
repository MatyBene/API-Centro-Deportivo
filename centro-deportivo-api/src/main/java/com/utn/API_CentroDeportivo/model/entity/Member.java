package com.utn.API_CentroDeportivo.model.entity;

import com.utn.API_CentroDeportivo.model.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class Member extends User{
    private Status status;
    private List<Enrollment> enrollments;
}
