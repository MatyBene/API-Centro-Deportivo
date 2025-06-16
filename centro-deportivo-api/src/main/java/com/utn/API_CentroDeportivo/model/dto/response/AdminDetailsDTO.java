package com.utn.API_CentroDeportivo.model.dto.response;

import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class AdminDetailsDTO extends UserDetailsDTO{
    private LocalDate hireDate;
    private PermissionLevel permissionLevel;
}
