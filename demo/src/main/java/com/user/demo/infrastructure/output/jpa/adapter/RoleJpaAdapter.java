package com.user.demo.infrastructure.output.jpa.adapter;

import com.user.demo.domain.model.Role;
import com.user.demo.domain.spi.IRolePersistencePort;
import com.user.demo.domain.util.Util;
import com.user.demo.infrastructure.output.jpa.entity.RoleEntity;
import com.user.demo.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository iRoleRepository;
    private final IRoleEntityMapper iRoleEntityMapper;
    @Override
    public Role getRoleById(Long id) {
        RoleEntity roleEntity = iRoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Util.ROLE_NOT_FUND));
        return iRoleEntityMapper.toBrand(roleEntity);
    }
}
