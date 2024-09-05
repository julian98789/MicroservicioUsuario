package com.user.demo.infrastructure.configuration.securityconfig;

import com.user.demo.application.dto.authenticationdto.AuthenticationRequest;
import com.user.demo.application.dto.authenticationdto.AuthenticationResponse;
import com.user.demo.infrastructure.configuration.securityconfig.jwtconfiguration.JwtService;
import com.user.demo.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.getToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
