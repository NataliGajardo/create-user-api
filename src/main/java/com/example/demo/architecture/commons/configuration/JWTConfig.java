package com.example.demo.architecture.commons.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTConfig{
    private final String SECRET_KEY = "privateToken";

public String createToken(String username,boolean isActive) {
    return Jwts.builder()
            .claim("username",username)
          //  .claim("isActive",isActive)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
}

public Boolean validateToken(String token, String username) {
    final String tokenUsername = extractUsername(token);
    return (tokenUsername.equals(username) && !isTokenExpired(token));
}

public String extractUsername(String token) {
    return extractAllClaims(token).get("username", String.class);
}

private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
}

Boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
}
}
