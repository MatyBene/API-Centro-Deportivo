package com.utn.API_CentroDeportivo.model.dto.response;

import com.utn.API_CentroDeportivo.model.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class MembersDetailsDTO extends UserDetailsDTO{
    private Status status;
    private List<EnrollmentDTO> enrollments;
}
