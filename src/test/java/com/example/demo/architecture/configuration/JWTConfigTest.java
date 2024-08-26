package com.example.demo.architecture.configuration;

import com.example.demo.architecture.commons.configuration.JWTConfig;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class JWTConfigTest {

    private JWTConfig jwtConfig;

    @BeforeEach
    void setUp() {

        jwtConfig = new JWTConfig();
    }

    @Test
    void testGenerateToken() {
        String username = "testUser";
        boolean isActive = true;
        String token = jwtConfig.createToken(username, isActive);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testValidateToken() {
        String username = "testUser";
        boolean isActive = true;
        String token = jwtConfig.createToken(username, isActive);

        assertTrue(jwtConfig.validateToken(token, username));
    }

    @Test
    void testExtractUsername() {
            String username = "testUser";
            boolean isActive = true;
            String token = jwtConfig.createToken(username, isActive);

            String extractedUsername = jwtConfig.extractUsername(token);
            assertEquals(username, extractedUsername);

    }

    @Test
    void testExpiredToken() {
        String expiredToken = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject("testUser")
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 11)) // 11 hours ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60)) // 1 hour ago
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "privateToken")
                .compact();

        assertThrows(io.jsonwebtoken.ExpiredJwtException.class, () -> {
            jwtConfig.validateToken(expiredToken, "testUser");
        });
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "invalidToken";

        assertThrows(io.jsonwebtoken.MalformedJwtException.class, () -> {
            jwtConfig.validateToken(invalidToken, "testUser");
        });
    }
}