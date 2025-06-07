package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByCredentialUsername(String username);
}
