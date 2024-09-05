package com.user.demo.infrastructure.configuration.securityconfig.jwtconfiguration;

import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtService {
    private static final String SECRET_KEY = "u2FsdGVkX19jY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2FhY2Fh\n";

    public String getToken(UserEntity userEntity) {
        // Usa el correo electrónico del usuario para generar el token
        return generateToken(new HashMap<>(), userEntity.getEmail(), userEntity.getRole().getName());
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            @NotNull String email,
            @NotNull String role
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(email)  // Usa el correo electrónico como sujeto
                .claim("role", role) // Incluye el rol en los claims
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Ajusta el tiempo de expiración si es necesario
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        // Extrae el correo electrónico del token
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractRole(String token) {
        // Extrae el rol del token
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public boolean isTokenValid(String token, UserEntity userEntity) {
        // Compara el correo electrónico extraído del token con el del usuario
        final String email = extractUsername(token);
        final String role = extractRole(token);
        System.out.println("EMAILL:" + email.equals(userEntity.getEmail()));
        System.out.println("Roleee:" + role.equals(userEntity.getRole().getName()));
        return (email.equals(userEntity.getEmail()) && role.equals(userEntity.getRole().getName()));


    }

    private Claims extractAllClaims(String token) throws SignatureException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            e.printStackTrace();
            return Jwts.claims(); // Devuelve un objeto Claims vacío en caso de error
        }
    }
}