package com.user.demo.infrastructure.output.jpa.adapter;

import com.user.demo.domain.model.Role;
import com.user.demo.domain.model.User;
import com.user.demo.infrastructure.output.jpa.entity.RoleEntity;
import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import com.user.demo.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Register a user and return the registered user with encoded password")
    void registerUser_shouldRegisterAndReturnUser() {
        Long id = 1L;
        String name = "John";
        String lastName = "Doe";
        String identification = "123456";
        String phone = "5551234567";
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        String email = "john.doe@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        Role role = new Role(1L, "User", "Regular user role");

        User user = new User(id, name, lastName, identification, phone, dateOfBirth, email, password, role);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName(name);
        userEntity.setLastName(lastName);
        userEntity.setIdentification(identification);
        userEntity.setPhone(phone);
        userEntity.setDateOfBirth(dateOfBirth);
        userEntity.setEmail(email);
        userEntity.setPassword(encodedPassword);
        userEntity.setRole(new RoleEntity());

        User returnedUser = new User(id, name, lastName, identification, phone, dateOfBirth, email, encodedPassword, role);

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toModel(userEntity)).thenReturn(returnedUser);


        User registeredUser = userJpaAdapter.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals(id, registeredUser.getId());
        assertEquals(name, registeredUser.getName());
        assertEquals(lastName, registeredUser.getLastName());
        assertEquals(identification, registeredUser.getIdentification());
        assertEquals(phone, registeredUser.getPhone());
        assertEquals(dateOfBirth, registeredUser.getDateOfBirth());
        assertEquals(email, registeredUser.getEmail());
        assertEquals(encodedPassword, registeredUser.getPassword());
        assertEquals(role, registeredUser.getRole());

        verify(passwordEncoder).encode(password);
        verify(userEntityMapper).toEntity(user);
        verify(userRepository).save(userEntity);
        verify(userEntityMapper).toModel(userEntity);
    }

    @Test
    @DisplayName("Should return true if user with the given identification exists")
    void existsByIdentification_UserExists() {

        String identification = "123456";
        when(userRepository.findByIdentification(identification)).thenReturn(Optional.of(new UserEntity()));

        boolean exists = userJpaAdapter.existsByIdentification(identification);

        assertTrue(exists);
        verify(userRepository).findByIdentification(identification);
    }

    @Test
    @DisplayName("Should return false if user with the given identification does not exist")
    void existsByIdentification_UserDoesNotExist() {

        String identification = "123456";
        when(userRepository.findByIdentification(identification)).thenReturn(Optional.empty());

        boolean exists = userJpaAdapter.existsByIdentification(identification);

        assertFalse(exists);
        verify(userRepository).findByIdentification(identification);
    }
}