package com.user.demo.infrastructure.output.jpa.mapper;

import com.user.demo.domain.model.Role;
import com.user.demo.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {

    RoleEntity toEntity(Role role);

    Role toBrand(RoleEntity roleEntity);

}
