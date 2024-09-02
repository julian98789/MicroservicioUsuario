package com.user.demo.application.mapper.rolemapper;

import com.user.demo.application.dto.roledto.RoleResponse;
import com.user.demo.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {

    RoleResponse roleResponseToResponse(Role role);

}
