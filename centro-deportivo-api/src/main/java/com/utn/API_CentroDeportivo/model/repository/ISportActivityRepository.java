package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISportActivityRepository extends JpaRepository<SportActivity, Long> {
}
