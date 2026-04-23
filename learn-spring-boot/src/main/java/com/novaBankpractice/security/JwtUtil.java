package com.novaBankpractice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Clave secreta para firmar los tokens (El sello del banco)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Tiempo de validez del token: 1 hora (en milisegundos)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 1. Generar un Token para un usuario
    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 2. Extraer el nombre de usuario de un Token
    public String extraerUsername(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    // 3. Comprobar si el Token ha caducado
    private boolean isTokenExpirado(String token) {
        return extraerClaim(token, Claims::getExpiration).before(new Date());
    }

    // 4. Validar que el token pertenece al usuario y no está caducado
    public boolean validarToken(String token, String username) {
        final String tokenUsername = extraerUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpirado(token));
    }

    private <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}