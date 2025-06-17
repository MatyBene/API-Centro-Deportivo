package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.SportActivityNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.ISportActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SportActivityServiceTest {

    @Mock
    private ISportActivityRepository sportActivityRepository;

    @InjectMocks
    private SportActivityService sportActivityService;

    private SportActivity sportActivity;
    private Instructor instructor;
    private final Long activityId = 1L;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setName("John");
        instructor.setLastname("Doe");

        sportActivity = new SportActivity();
        sportActivity.setId(activityId);
        sportActivity.setName("Yoga");
        sportActivity.setInstructor(instructor);
        sportActivity.setMaxMembers(20);
        sportActivity.setEnrollments(Collections.singletonList(new Enrollment()));
    }

    /**
     * Prueba unitaria para el método getActivities de SportActivityService.
     * Verifica que se retorne una página de actividades deportivas correctamente mapeadas a SportActivitySummaryDTO.
     * <p>
     * Escenario:
     * - Se simula una página con una sola actividad.
     * - Se verifica que el resultado no sea nulo, tenga un elemento y que el nombre coincida.
     * - Se asegura que el repositorio sea invocado una vez.
     */
    @Test
    void getActivities_ShouldReturnPageOfActivities() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
        Page<SportActivity> activityPage = new PageImpl<>(Collections.singletonList(sportActivity));
        when(sportActivityRepository.findAll(pageable)).thenReturn(activityPage);

        // Act
        Page<SportActivitySummaryDTO> result = sportActivityService.getActivities(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Yoga", result.getContent().get(0).getName());
        verify(sportActivityRepository, times(1)).findAll(pageable);
    }

    /**
     * Prueba unitaria para el método findActivitiesByName de SportActivityService.
     * Verifica que se retorne una página filtrada de actividades deportivas cuyo nombre contiene el valor dado (ignorando mayúsculas/minúsculas).
     * <p>
     * Escenario:
     * - Se simula una página con una sola actividad cuyo nombre coincide.
     * - Se verifica que el resultado no sea nulo, tenga un elemento y que el nombre coincida.
     * - Se asegura que el repositorio sea invocado una vez con los parámetros correctos.
     */
    @Test
    void findActivitiesByName_ShouldReturnFilteredPage() {
        // Arrange
        String name = "Yoga";
        Pageable pageable = PageRequest.of(0, 5);
        Page<SportActivity> activityPage = new PageImpl<>(Collections.singletonList(sportActivity));
        when(sportActivityRepository.findByNameContainingIgnoreCase(name, pageable)).thenReturn(activityPage);

        // Act
        Page<SportActivitySummaryDTO> result = sportActivityService.findActivitiesByName(name, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(name, result.getContent().get(0).getName());
        verify(sportActivityRepository, times(1)).findByNameContainingIgnoreCase(name, pageable);
    }

    /**
     * Prueba unitaria para el método findActivitiesByTimeRange de SportActivityService.
     * Verifica que se retorne una página filtrada de actividades deportivas cuyo horario se solapa con el rango dado.
     * <p>
     * Escenario:
     * - Se simula una página con una sola actividad dentro del rango horario.
     * - Se verifica que el resultado no sea nulo y contenga un elemento.
     * - Se asegura que el repositorio sea invocado una vez con los parámetros correctos.
     */
    @Test
    void findActivitiesByTimeRange_ShouldReturnFilteredPage() {
        // Arrange
        String startTimeStr = "09:00";
        String endTimeStr = "12:00";
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);
        Pageable pageable = PageRequest.of(0, 5);
        Page<SportActivity> activityPage = new PageImpl<>(Collections.singletonList(sportActivity));
        when(sportActivityRepository.findByTimeRangeOverlap(startTime, endTime, pageable)).thenReturn(activityPage);

        // Act
        Page<SportActivitySummaryDTO> result = sportActivityService.findActivitiesByTimeRange(startTimeStr, endTimeStr, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(sportActivityRepository, times(1)).findByTimeRangeOverlap(startTime, endTime, pageable);
    }

    /**
     * Prueba unitaria para el método getActivityById de SportActivityService.
     * Verifica que se retorne un Optional con los detalles de la actividad cuando existe.
     * <p>
     * Escenario:
     * - El repositorio retorna una actividad existente.
     * - Se espera que el resultado esté presente, que el nombre sea "Yoga" y que los miembros actuales sean 1.
     * - Se verifica que el repositorio sea invocado dos veces.
     */
    @Test
    void getActivityById_ShouldReturnDetails_WhenActivityExists() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.of(sportActivity));

        // Act
        Optional<SportActivityDetailsDTO> result = sportActivityService.getActivityById(activityId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Yoga", result.get().getName());
        assertEquals(1, result.get().getCurrentMembers());
        verify(sportActivityRepository, times(2)).findById(activityId);
    }

    /**
     * Prueba unitaria para el método getActivityById de SportActivityService.
     * Verifica que se retorne un Optional vacío cuando la actividad no se encuentra.
     * <p>
     * Escenario:
     * - El repositorio retorna un Optional vacío.
     * - Se espera que el resultado no esté presente.
     * - Se verifica que el repositorio sea invocado una vez.
     */
    @Test
    void getActivityById_ShouldReturnEmpty_WhenActivityNotFound() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.empty());

        // Act
        Optional<SportActivityDetailsDTO> result = sportActivityService.getActivityById(activityId);

        // Assert
        assertFalse(result.isPresent());
        verify(sportActivityRepository, times(1)).findById(activityId);
    }

    /**
     * Prueba unitaria para el método getCurrentMembers de SportActivityService.
     * Verifica que se retorne la cantidad de miembros actuales cuando la actividad existe.
     * <p>
     * Escenario:
     * - El repositorio retorna una actividad existente con una inscripción.
     * - Se espera que el resultado sea 1.
     * - Se verifica que el repositorio sea invocado una vez.
     */
    @Test
    void getCurrentMembers_ShouldReturnMemberCount_WhenActivityExists() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.of(sportActivity));

        // Act
        int count = sportActivityService.getCurrentMembers(activityId);

        // Assert
        assertEquals(1, count);
        verify(sportActivityRepository, times(1)).findById(activityId);
    }

    /**
     * Prueba unitaria para el método getCurrentMembers de SportActivityService.
     * Verifica que se retorne 0 cuando la actividad no se encuentra.
     * <p>
     * Escenario:
     * - El repositorio retorna un Optional vacío.
     * - Se espera que el resultado sea 0.
     * - Se verifica que el repositorio sea invocado una vez.
     */
    @Test
    void getCurrentMembers_ShouldReturnZero_WhenActivityNotFound() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.empty());

        // Act
        int count = sportActivityService.getCurrentMembers(activityId);

        // Assert
        assertEquals(0, count);
        verify(sportActivityRepository, times(1)).findById(activityId);
    }

    /**
     * Prueba unitaria para el método getActivitiesByInstructor de SportActivityService.
     * Verifica que se retorne una lista de resúmenes de actividades deportivas asociadas a un instructor dado.
     * <p>
     * Escenario:
     * - El repositorio retorna una lista con una sola actividad.
     * - Se espera que el resultado no sea nulo, tenga un elemento y que el nombre coincida.
     * - Se verifica que el repositorio sea invocado una vez con el instructor proporcionado.
     */
    @Test
    void getActivitiesByInstructor_ShouldReturnSummaryList() {
        // Arrange
        when(sportActivityRepository.findByInstructor(instructor)).thenReturn(Collections.singletonList(sportActivity));

        // Act
        List<SportActivitySummaryDTO> result = sportActivityService.getActivitiesByInstructor(instructor);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Yoga", result.get(0).getName());
        verify(sportActivityRepository, times(1)).findByInstructor(instructor);
    }

    /**
     * Prueba unitaria para el método getActivitiesDetailsByInstructor de SportActivityService.
     * Verifica que se retorne una lista de detalles de actividades deportivas asociadas a un instructor dado.
     * <p>
     * Escenario:
     * - El repositorio retorna una lista con una sola actividad.
     * - Se espera que el resultado no sea nulo, tenga un elemento y que el nombre coincida.
     * - Se verifica que el repositorio sea invocado una vez con el instructor proporcionado.
     */
//    @Test
//    void getActivitiesDetailsByInstructor_ShouldReturnDetailsList() {
//        // Arrange
//        when(sportActivityRepository.findByInstructor(instructor)).thenReturn(Collections.singletonList(sportActivity));
//
//        // Act
//        List<SportActivityDetailsDTO> result = sportActivityService.getActivityDetailsByInstructor(instructor);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("Yoga", result.get(0).getName());
//        verify(sportActivityRepository, times(1)).findByInstructor(instructor);
//    }

    /**
     * Prueba unitaria para el método getSportActivityEntityById de SportActivityService.
     * Verifica que se retorne un Optional con la entidad SportActivity cuando existe.
     * <p>
     * Escenario:
     * - El repositorio retorna una actividad existente.
     * - Se espera que el resultado esté presente y que la entidad coincida.
     * - Se verifica que el repositorio sea invocado una vez.
     */
    @Test
    void getSportActivityById_ShouldReturnActivity_WhenExists() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.of(sportActivity));

        // Act
        Optional<SportActivity> result = sportActivityService.getSportActivityEntityById(activityId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(sportActivity, result.get());
        verify(sportActivityRepository, times(1)).findById(activityId);
    }

    /**
     * Prueba unitaria para el método getSportActivityEntityById de SportActivityService.
     * Verifica que se lance una excepción SportActivityNotFoundException cuando la actividad no se encuentra.
     * <p>
     * Escenario:
     * - El repositorio retorna un Optional vacío.
     * - Se espera que se lance la excepción con el mensaje "Actividad no encontrada".
     * - Se verifica que el repositorio sea invocado una vez.
     */
    @Test
    void getSportActivityById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(sportActivityRepository.findById(activityId)).thenReturn(Optional.empty());

        // Act & Assert
        SportActivityNotFoundException exception = assertThrows(SportActivityNotFoundException.class, () -> {
            sportActivityService.getSportActivityEntityById(activityId);
        });
        assertEquals("Actividad no encontrada", exception.getMessage());
        verify(sportActivityRepository, times(1)).findById(activityId);
    }
}