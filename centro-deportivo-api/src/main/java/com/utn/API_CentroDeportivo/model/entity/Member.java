package com.utn.API_CentroDeportivo.model.entity;

import com.utn.API_CentroDeportivo.model.enums.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Member extends User{
    private Status status;
    private List<Enrollment> enrollments;
}
