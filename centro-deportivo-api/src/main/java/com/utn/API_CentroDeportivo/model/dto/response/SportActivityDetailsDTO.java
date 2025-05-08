package com.utn.API_CentroDeportivo.model.dto.response;

import com.utn.API_CentroDeportivo.model.enums.Day;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SportActivityDetailsDTO extends SportActivitySummaryDTO{
    private String description;
    private int maxMembers;
    private int currentMembers;
    private String startTime;
    private String endTime;
    private List<Day> classDays;
}
