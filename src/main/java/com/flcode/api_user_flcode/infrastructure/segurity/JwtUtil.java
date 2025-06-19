package com.flcode.api_user_flcode.infrastructure.segurity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("JWT_SECRET=X8M+zQo8n0C5dFeA8gUOZDA1lFZG04xvDUTYxkLK8b8=".getBytes());

    private static final long EXPIRATION_TIME = 86400000;

    private static final JwtParser parser = Jwts.parser()
            .verifyWith((SecretKey) SECRET_KEY)
            .build();

    public static String generateToken(String userId, String email) {
        return Jwts.builder()
                .subject(userId)
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims getClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }


    public static boolean validateToken(String token) {
        try {
            parser.parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUserId(String token) {
        return getClaims(token).getSubject();
    }

}
