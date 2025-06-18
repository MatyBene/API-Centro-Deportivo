package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.service.IAdminService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/v1/enrollments")
@Tag(name = "Gestión de Inscripciones", description = "Endpoints para la gestión de inscripciones a actividades deportivas (requiere rol de INSTRUCTOR).")
@SecurityRequirement(name = "Bearer Authentication")
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private ICredentialService credentialService;

    @Operation(
            summary = "Remover un miembro de una actividad",
            description = "Permite a un instructor remover a un miembro específico de una actividad deportiva. Requiere los IDs de la actividad y del miembro.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "El socio se dio de baja con éxito",
                            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "El socio se dio de baja con éxito."))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Acceso denegado (requiere rol de INSTRUCTOR)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Inscripción, actividad o miembro no encontrado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{activityId}/members/{memberId}")
    public ResponseEntity<String> removeMemberFromActivity(
            @PathVariable Long activityId,
            @PathVariable Long memberId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long instructorId = credentialService.getUserByUsername(username).getId();

        enrollmentService.cancelEnrollment(instructorId, activityId, memberId);
        return ResponseEntity.ok("El socio se dio de baja con éxito.");
    }

    @Operation(
            summary = "Inscribir un miembro a una actividad gestionada por el instructor",
            description = "Permite a un instructor inscribir a un miembro específico en una de las actividades que él/ella gestiona.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Socio inscripto correctamente a la actividad",
                            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Socio inscripto correctamente a la actividad"))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida (ej. actividad no existe, miembro ya inscrito, etc.)"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Acceso denegado (requiere rol de INSTRUCTOR o la actividad no es gestionada por el instructor)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Actividad o miembro no encontrado"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicto (ej. el miembro ya está inscrito en la actividad)"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/my-activities/{activityId}/enroll/{memberId}")
    public ResponseEntity<String> enrollMemberToMyActivity(@PathVariable Long activityId, @PathVariable Long memberId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        enrollmentService.enrollMemberToActivityByInstructor(username, activityId, memberId);
        return ResponseEntity.ok("Socio inscripto correctamente a la actividad");
    }

}
