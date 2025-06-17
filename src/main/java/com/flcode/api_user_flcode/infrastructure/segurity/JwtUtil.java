package com.flcode.api_user_flcode.infrastructure.segurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Puedes almacenar esto en un archivo de configuración o variable de entorno
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("JWT_SECRET=X8M+zQo8n0C5dFeA8gUOZDA1lFZG04xvDUTYxkLK8b8=".getBytes());

    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public static String generateToken(String userId, String email) {
        return Jwts.builder()
                .subject(userId)
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
