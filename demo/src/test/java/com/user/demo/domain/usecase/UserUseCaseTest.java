package com.user.demo.domain.usecase;

import com.user.demo.domain.exception.EmailAlreadyExistsException;
import com.user.demo.domain.exception.IdentificationAlreadyExistsException;
import com.user.demo.domain.exception.UserNotOfLegalAge;
import com.user.demo.domain.model.Role;
import com.user.demo.domain.model.User;
import com.user.demo.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort iUserPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private Role defaultRole;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        defaultRole = new Role(1L, "USER","user");
    }
    @Test
    @DisplayName("Should successfully register a valid user")
     void testRegisterUser_ValidUser() {
        User user = new User(1L, "John", "Doe", "123456", "555-1234",
                LocalDate.of(2000, 1, 1), "john.doe@example.com", "password", defaultRole);

        when(iUserPersistencePort.existsByIdentification(user.getIdentification())).thenReturn(false);
        when(iUserPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(iUserPersistencePort.registerUser(any(User.class))).thenReturn(user);

        User result = userUseCase.registerUser(user);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Should throw UserNotOfLegalAge exception for users under 18")
     void testRegisterUser_UserNotOfLegalAge() {
        User user = new User(1L, "John", "Doe", "123456", "555-1234",
                LocalDate.now().plusYears(1), "john.doe@example.com", "password", defaultRole);

        when(iUserPersistencePort.existsByIdentification(user.getIdentification())).thenReturn(false);
        when(iUserPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        assertThrows(UserNotOfLegalAge.class, () -> userUseCase.registerUser(user));
    }

    @Test
    @DisplayName("Should throw IdentificationAlreadyExistsException for existing identification")
     void testRegisterUser_IdentificationAlreadyExists() {
        User user = new User(1L, "John", "Doe", "123456", "555-1234",
                LocalDate.of(2000, 1, 1), "john.doe@example.com", "password", defaultRole);

        when(iUserPersistencePort.existsByIdentification(user.getIdentification())).thenReturn(true);
        when(iUserPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        assertThrows(IdentificationAlreadyExistsException.class, () -> userUseCase.registerUser(user));
    }

    @Test
    @DisplayName("Should throw EmailAlreadyExistsException for existing email")
     void testRegisterUser_EmailAlreadyExists() {
        User user = new User(1L, "John", "Doe", "123456", "555-1234",
                LocalDate.of(2000, 1, 1), "john.doe@example.com", "password", defaultRole);

        when(iUserPersistencePort.existsByIdentification(user.getIdentification())).thenReturn(false);
        when(iUserPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.registerUser(user));
    }
}