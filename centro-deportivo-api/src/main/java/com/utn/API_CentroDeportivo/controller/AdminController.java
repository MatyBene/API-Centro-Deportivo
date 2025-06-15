package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.AdminViewDTO;
import com.utn.API_CentroDeportivo.model.enums.PermissionLevel;
import com.utn.API_CentroDeportivo.model.enums.Role;
import com.utn.API_CentroDeportivo.model.enums.Status;
import com.utn.API_CentroDeportivo.service.IAdminService;
import com.utn.API_CentroDeportivo.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-member")
    public ResponseEntity<String> createMember(@RequestBody UserRequestDTO userDTO) {
        adminService.createUser(userDTO);
        return ResponseEntity.ok("Socio creado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN') and (hasAuthority('PERMISSION_USER_MANAGER') or hasAuthority('PERMISSION_SUPER_ADMIN'))")
    @PostMapping("/create-instructor")
    public ResponseEntity<String> createInstructor(@RequestBody UserRequestDTO userDTO) {
        adminService.createUser(userDTO);
        return ResponseEntity.ok("Instructor creado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION_SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdmin(@RequestBody UserRequestDTO userDTO) {
        adminService.createUser(userDTO);
        return ResponseEntity.ok("Admin creado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN') and (hasAuthority('PERMISSION_SUPER_ADMIN') or hasAuthority('PERMISSION_USER_MANAGER'))")
    @PostMapping("/enroll-member")
    public ResponseEntity<String> enrollMemberInActivity(@RequestBody EnrollmentRequestDTO request) {
        enrollmentService.enrollMemberToActivity(request.getUsername(), request.getActivityId());
        return ResponseEntity.ok("El socio se inscribió correctamente en la actividad");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<Page<AdminViewDTO>> getUsers(
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) PermissionLevel permission,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<AdminViewDTO> dtoPage = adminService.getUsers(role, status, permission, pageable);
        return ResponseEntity.ok(dtoPage);
    }
}
