package com.user.demo.application.mapper.usermapper;

import com.user.demo.application.dto.userdto.UserResponse;
import com.user.demo.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(target = "role", source = "role")
    UserResponse toUserResponseDto(User user);

}
