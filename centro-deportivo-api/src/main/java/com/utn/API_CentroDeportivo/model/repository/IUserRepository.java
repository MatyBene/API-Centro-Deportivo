package com.utn.API_CentroDeportivo.model.repository;

import com.utn.API_CentroDeportivo.model.entity.Member;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.credential c WHERE c.role = :role")
    Page<User> findByRole(@Param("role") Role role, Pageable pageable);

    @Query("SELECT u FROM Member u JOIN u.credential c WHERE c.role = :role AND u.status = :status")
    Page<User> findByRoleAndStatus(@Param("role") Role role, @Param("status") Status status, Pageable pageable);

    @Query("SELECT u FROM Admin u JOIN u.credential c WHERE c.role = :role AND u.permissionLevel = :permission")
    Page<User> findByRoleAndPermission(@Param("role") Role role, @Param("permission") PermissionLevel permission, Pageable pageable);
}
