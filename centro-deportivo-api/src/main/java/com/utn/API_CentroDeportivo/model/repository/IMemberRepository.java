package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepository extends JpaRepository<Member, Long> {
}
