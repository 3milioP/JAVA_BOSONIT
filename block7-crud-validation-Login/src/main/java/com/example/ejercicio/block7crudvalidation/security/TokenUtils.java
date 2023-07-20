package com.example.ejercicio.block7crudvalidation.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "443A762B3758F1542618C69B42ADB5F2";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    //Crear un token con el nombre, el rol y la fecha de expiración
    public static String createToken(String nombre, String role) {

        Long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("role", role);

        return Jwts.builder()
                .setSubject(nombre)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
}
