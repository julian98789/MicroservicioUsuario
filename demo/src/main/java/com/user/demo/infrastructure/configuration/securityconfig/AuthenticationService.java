package com.user.demo.infrastructure.configuration.securityconfig;

import com.user.demo.application.dto.authenticationdto.AuthenticationRequest;
import com.user.demo.application.dto.authenticationdto.AuthenticationResponse;
import com.user.demo.domain.exception.AccountLockedException;
import com.user.demo.domain.exception.InvalidCredentialsLoginException;
import com.user.demo.domain.exception.TooManyAttemptsException;
import com.user.demo.domain.util.Util;
import com.user.demo.infrastructure.configuration.securityconfig.jwtconfiguration.JwtService;
import com.user.demo.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.user.demo.domain.util.Util.LOCK_TIME_DURATION;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IUserEntityMapper userEntityMapper;

    private final Map<String, Integer> failedAttempts = new ConcurrentHashMap<>();
    private final Map<String, Long> lockTime = new ConcurrentHashMap<>();

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email = request.getEmail();

        if (lockTime.containsKey(email)) {
            long lockEndTime = lockTime.get(email);
            if (System.currentTimeMillis() < lockEndTime) {
                throw new AccountLockedException(Util.ACCOUNT_LOCKED);
            } else {
                failedAttempts.remove(email);
                lockTime.remove(email);
            }
        }

        var userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            var user = userEntityMapper.toModel(userOptional.get());
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                failedAttempts.remove(email);
                var jwtToken = jwtService.getToken(user);

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
        }

        int attempts = failedAttempts.getOrDefault(email, 0) + 1;
        failedAttempts.put(email, attempts);

        if (attempts >= Util.MAX_TIME) {
            lockTime.put(email, System.currentTimeMillis() + LOCK_TIME_DURATION);
            throw new TooManyAttemptsException(Util.TOO_MANY_ATTEMPTS);
        }

        throw new InvalidCredentialsLoginException(Util.INVALID_CREDENTIALS + (Util.MAX_TIME - attempts));
    }
}