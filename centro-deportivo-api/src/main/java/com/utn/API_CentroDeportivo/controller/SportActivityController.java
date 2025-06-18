package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1/activities")
@Tag(name = "Actividades Deportivas", description = "Endpoints para la consulta de actividades deportivas.")
public class SportActivityController {

    @Autowired
    private ISportActivityService sportActivityService;

    @Operation(
            summary = "Obtener todas las actividades deportivas paginadas",
            description = "Recupera una lista paginada de todas las actividades deportivas disponibles, mostrando un resumen de cada una.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de actividades recuperada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class, subTypes = {SportActivitySummaryDTO.class})
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @GetMapping()
    public ResponseEntity<Page<SportActivitySummaryDTO>> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.getActivities(pageable);
        return ResponseEntity.ok(activities);
    }

    @Operation(
            summary = "Obtener detalles de una actividad deportiva por ID",
            description = "Recupera los detalles completos de una actividad deportiva específica utilizando su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalles de la actividad recuperados exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SportActivityDetailsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Actividad no encontrada con el ID proporcionado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SportActivityDetailsDTO> getActivity(@PathVariable Long id) {
        return sportActivityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Buscar actividades deportivas por nombre",
            description = "Busca actividades deportivas que contengan el nombre especificado, con paginación.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de actividades encontradas exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class, subTypes = {SportActivitySummaryDTO.class})
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )

    @GetMapping("/search")
    public ResponseEntity<Page<SportActivitySummaryDTO>> searchActivitiesByName(@RequestParam String name,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.findActivitiesByName(name, pageable);
        return ResponseEntity.ok(activities);
    }

    @Operation(
            summary = "Buscar actividades deportivas por rango de tiempo",
            description = "Busca actividades deportivas que ocurran dentro de un rango de tiempo especificado, con paginación. Los tiempos deben estar en formato HH:mm.",
            parameters = {
                    @Parameter(name = "startTime", description = "Hora de inicio del rango (formato HH:mm)", example = "09:00", required = true),
                    @Parameter(name = "endTime", description = "Hora de fin del rango (formato HH:mm)", example = "17:00", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de actividades encontradas exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class, subTypes = {SportActivitySummaryDTO.class})
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Formato de hora inválido. Asegúrese de usar HH:mm."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @GetMapping("/search-by-time")
    public ResponseEntity<Page<SportActivitySummaryDTO>> searchActivitiesByTimeRange(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SportActivitySummaryDTO> activities = sportActivityService.findActivitiesByTimeRange(startTime, endTime, pageable);
        return ResponseEntity.ok(activities);
    }
}
