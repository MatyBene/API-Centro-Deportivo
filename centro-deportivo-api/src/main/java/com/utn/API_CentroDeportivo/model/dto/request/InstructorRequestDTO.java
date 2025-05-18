package com.utn.API_CentroDeportivo.model.dto.request;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class InstructorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @NotBlank(message = "El dni es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener al menos 8 dígitos")
    private String dni;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha de nacimiento debe ser YYYY-MM-DD")
    private String birthdate;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 dígitos")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos: 1 mayúscula, 1 minúscula, 1 número, 1 carácter especial y no tener espacios"
    )
    private String password;

    @NotBlank(message = "La especialidad es obligatoria")
    private String specialty;
}
