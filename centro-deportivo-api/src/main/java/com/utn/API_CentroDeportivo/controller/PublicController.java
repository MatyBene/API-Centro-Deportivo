package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.LoginRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.MemberRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.LoginResponseDTO;
import com.utn.API_CentroDeportivo.service.IAuthService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IJwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
@Tag(name = "Autenticación y Registro Público", description = "Endpoints para registro de nuevos miembros y autenticación de usuarios.")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private ICredentialService credentialService;

    @Operation(
            summary = "Registrar un nuevo miembro",
            description = "Permite a un usuario registrarse como nuevo miembro en el sistema. Se crea una nueva cuenta de miembro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del miembro a registrar (nombre, apellido, email, contraseña, etc.)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MemberRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "El socio se creó correctamente",
                            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "El socio se creo correctamente"))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida o datos de registro incompletos/incorrectos"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicto: el email o nombre de usuario ya existen"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )

    @PostMapping("/register")
    public ResponseEntity<String> createMember(@Valid @RequestBody MemberRequestDTO memberDTO) {
        authService.registerMember(memberDTO);
        return ResponseEntity.ok("El socio se creo correctamente");
    }

    @Operation(
            summary = "Iniciar sesión de usuario",
            description = "Permite a un usuario autenticarse en el sistema y obtener un token JWT para acceder a recursos protegidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciales de usuario (nombre de usuario y contraseña)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inicio de sesión exitoso, devuelve un token JWT",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciales inválidas (usuario o contraseña incorrectos)"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){

        UserDetails user = credentialService.loadUserByUsername(request.getUsername());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
