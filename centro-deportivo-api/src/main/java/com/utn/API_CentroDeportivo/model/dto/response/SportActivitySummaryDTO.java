package com.utn.API_CentroDeportivo.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class SportActivitySummaryDTO {
    private String name;
    private int maxMembers;
    private String instructorName;
}
