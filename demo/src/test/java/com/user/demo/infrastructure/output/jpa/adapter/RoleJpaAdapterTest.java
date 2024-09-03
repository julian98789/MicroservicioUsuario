package com.user.demo.infrastructure.output.jpa.adapter;

import com.user.demo.domain.model.Role;
import com.user.demo.infrastructure.output.jpa.entity.RoleEntity;
import com.user.demo.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RoleJpaAdapterTest {


    @Mock
    private IRoleRepository iRoleRepository;

    @Mock
    private IRoleEntityMapper iRoleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    private RoleEntity roleEntity;
    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roleEntity = new RoleEntity(1L, "Admin", "Administrator role");
        role = new Role(1L, "Admin", "Administrator role");
    }

    @Test
    @DisplayName("Should return Role when RoleEntity is found")
     void testGetRoleById_Found() {
        when(iRoleRepository.findById(anyLong())).thenReturn(java.util.Optional.of(roleEntity));
        when(iRoleEntityMapper.toBrand(roleEntity)).thenReturn(role);

        Role result = roleJpaAdapter.getRoleById(1L);

        assertEquals(role, result);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when RoleEntity is not found")
     void testGetRoleById_NotFound() {
        when(iRoleRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> roleJpaAdapter.getRoleById(1L));
    }
}
