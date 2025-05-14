package com.utn.API_CentroDeportivo.model.entity;

import com.utn.API_CentroDeportivo.model.enums.Day;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString

public class SportActivity {
    private Long id;
    private String name;
    private String description;
    private int maxMembers;
    private LocalTime startTime;
    private LocalTime endTime;
    private Instructor instructor;
    private List<Enrollment> enrollments;
    private List<Day> classDays;
}
