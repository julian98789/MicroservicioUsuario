package com.user.demo.infrastructure.configuration.securityconfig.jwtconfiguration;

import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String jwt = authHeader.substring(7);
            final String email = jwtService.extractUsername(jwt);  // Usamos extractUsername para extraer el correo

            // Logs para depurar
            System.out.println("JWT Token: " + jwt);
            System.out.println("Extracted Email: " + email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    // Cargamos el UserDetails usando el email
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

                    // Logs para depurar
                    System.out.println("UserDetails loaded: " + userDetails);

                    // Validamos el token usando el UserDetails y su correo
                    if (jwtService.isTokenValid(jwt, (UserEntity) userDetails)) {  // Cast a UserEntity
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (UsernameNotFoundException e) {
                    // Logs para depurar
                    System.out.println("User not found: " + email);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
