package com.utn.API_CentroDeportivo.controller;

import com.utn.API_CentroDeportivo.model.dto.request.LoginRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.request.UserRequestDTO;
import com.utn.API_CentroDeportivo.model.dto.response.LoginResponseDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.service.IAuthService;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IJwtService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/public")

public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private ISportActivityService sportActivityService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private ICredentialService credentialService;

    @PostMapping("/register")
    public ResponseEntity<String> createMember(@Valid @RequestBody UserRequestDTO memberDTO) {
        authService.registerMember(memberDTO);
        return ResponseEntity.ok("El socio se creo correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        UserDetails user = credentialService.loadUserByUsername(request.getUsername());

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/activities")
    public ResponseEntity<List<SportActivitySummaryDTO>> getActivities() {
        List<SportActivitySummaryDTO> activities = sportActivityService.getActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<SportActivityDetailsDTO> getActivity(@PathVariable Long id) {
        return sportActivityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
