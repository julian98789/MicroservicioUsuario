package com.user.demo.infrastructure.configuration.securityconfig;


import com.user.demo.domain.util.Util;
import com.user.demo.infrastructure.configuration.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF (si es necesario)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/stock/category").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/stock/category").hasAuthority(Util.USER_ROLE_ADMIN)
                        .requestMatchers(HttpMethod.GET,"/auth/stock/brand").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/stock/brand").hasAuthority(Util.USER_ROLE_ADMIN)
                        .requestMatchers(HttpMethod.GET,"/auth/stock/article").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/stock/article").hasAuthority(Util.USER_ROLE_ADMIN)
                        .requestMatchers("/auth/register-assistant").hasAuthority(Util.USER_ROLE_ADMIN)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

