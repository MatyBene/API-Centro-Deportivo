package com.utn.API_CentroDeportivo.model.dto.request;

import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.enums.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberRequestDTO {
    private Status status = Status.INACTIVE;
    private List<Enrollment> enrollments = new ArrayList<>();
}
