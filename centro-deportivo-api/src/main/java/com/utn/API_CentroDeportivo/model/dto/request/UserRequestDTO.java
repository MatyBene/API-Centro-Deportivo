package com.utn.API_CentroDeportivo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;
    @NotBlank(message = "El dni es obligatorio")
    @Min(value = 1000000, message = "El DNI debe tener al menos 7 dígitos")
    private String dni;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private String birthdate;
    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;
}
