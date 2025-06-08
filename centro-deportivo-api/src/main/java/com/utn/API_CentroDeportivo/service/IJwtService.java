package com.utn.API_CentroDeportivo.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

public interface IJwtService {
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    Claims extractAllClaims(String token);
    Key getKey();
}
