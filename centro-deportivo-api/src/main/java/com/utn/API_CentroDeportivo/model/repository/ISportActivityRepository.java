package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISportActivityRepository extends JpaRepository<SportActivity, Long> {
    List<SportActivity> findByInstructor(Instructor instructor);
    Page<SportActivity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
