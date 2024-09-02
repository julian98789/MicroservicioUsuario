package com.user.demo.application.mapper.rolemapper;

import com.user.demo.application.dto.userdto.UserRequest;
import com.user.demo.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(target = "role", ignore = true)
    User userRequestToUser(UserRequest userRequest);


}
