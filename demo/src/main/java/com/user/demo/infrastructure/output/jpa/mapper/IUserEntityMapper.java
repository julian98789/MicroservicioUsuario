package com.user.demo.infrastructure.output.jpa.mapper;


import com.user.demo.domain.model.User;
import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(target = "role", source = "role")
    UserEntity toEntity(User user);


    @Mapping(target = "role", source = "role")
    User toModel(UserEntity userEntity);
}
