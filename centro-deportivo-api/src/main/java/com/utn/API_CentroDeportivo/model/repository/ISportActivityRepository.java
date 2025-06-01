package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISportActivityRepository extends JpaRepository<SportActivity, Long> {
    @Query("SELECT * FROM sport_activity SA JOIN instructor i  WHERE i.id = SA.instructor_id")
    List<SportActivity> findByInstructor(@Param("instructor_id") Long instructorId);

}
