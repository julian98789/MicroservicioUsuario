package com.user.demo.application.dto.authenticationdto;

import com.user.demo.domain.util.Util;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = Util.EMAIL_NOT_BLANK)
    @Email(message = Util.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = Util.PASSWORD_NOT_BLANK)
    private String password;
}