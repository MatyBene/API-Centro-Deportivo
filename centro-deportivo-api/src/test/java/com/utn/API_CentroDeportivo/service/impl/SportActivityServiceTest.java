package com.utn.API_CentroDeportivo.service.impl;

import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Enrollment;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.exception.InvalidTimeFormatException;
import com.utn.API_CentroDeportivo.model.exception.SportActivityNotFoundException;
import com.utn.API_CentroDeportivo.model.repository.ISportActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
    private final String instructorName = "instructorName";
    private final String instructorLastname = "instructorLastname";
    private final String activityName = "Yoga";
    private final Integer maxMembers = 20;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setName(instructorName);
        instructor.setLastname(instructorLastname);

        sportActivity = new SportActivity();
        sportActivity.setId(activityId);
        sportActivity.setName(activityName);
        sportActivity.setInstructor(instructor);
        sportActivity.setMaxMembers(maxMembers);
        sportActivity.setEnrollments(Collections.singletonList(new Enrollment()));
    }

    @Nested
    class GetActivitiesTests {
        @Test
        void whenCalled_ShouldReturnPageOfSummaryDTOs() {
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
    }

    @Nested
    class FindActivitiesByNameTests {
        @Test
        void whenNameMatches_ShouldReturnFilteredPageOfSummaryDTOs() {
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
    }

    @Nested
    class FindActivitiesByTimeRangeTests {
        @Test
        void findActivitiesByTimeRange_WhenActivitiesExistInRange_ShouldReturnFilteredPageOfSummaryDTOs() {
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

        @Test
        void findActivitiesByTimeRange_WhenTimeFormatIsInvalid_ShouldThrowInvalidTimeFormatException() {
            // Arrange
            String invalidTime = "formato-invalido";
            Pageable pageable = PageRequest.of(0, 5);

            // Act & Assert
            assertThrows(InvalidTimeFormatException.class, () -> {
                sportActivityService.findActivitiesByTimeRange(invalidTime, "12:00", pageable);
            });
        }
    }

    @Nested
    class GetActivityByIdTests {
        @Test
        void getActivityById_WhenActivityExists_ShouldReturnOptionalWithDetailsDTO() {
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

        @Test
        void getActivityById_WhenActivityNotFound_ShouldReturnEmptyOptional() {
            // Arrange
            when(sportActivityRepository.findById(activityId)).thenReturn(Optional.empty());

            // Act
            Optional<SportActivityDetailsDTO> result = sportActivityService.getActivityById(activityId);

            // Assert
            assertFalse(result.isPresent());
            verify(sportActivityRepository, times(1)).findById(activityId);
        }
    }
}