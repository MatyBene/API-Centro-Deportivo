package com.utn.API_CentroDeportivo.model.controller;

import com.utn.API_CentroDeportivo.model.dto.request.CreateMemberDTO;
import com.utn.API_CentroDeportivo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> createMember(@RequestBody CreateMemberDTO memberDTO){
        authService.registerMember(memberDTO);
        return ResponseEntity.ok("El socio se creo correctamente");
    }
}
