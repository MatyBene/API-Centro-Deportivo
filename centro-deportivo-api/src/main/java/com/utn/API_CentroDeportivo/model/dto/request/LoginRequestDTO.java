package com.utn.API_CentroDeportivo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class LoginRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
