package com.user.demo.application.handler.user_microservice_handler;

import com.user.demo.application.dto.user_microservice_dto.userdto.UserRequest;
import com.user.demo.application.dto.user_microservice_dto.userdto.UserResponse;
import com.user.demo.application.mapper.usermapper.IUserRequestMapper;
import com.user.demo.application.mapper.usermapper.IUserResponseMapper;
import com.user.demo.domain.api.IUserServicePort;
import com.user.demo.domain.model.Role;
import com.user.demo.domain.model.User;
import com.user.demo.domain.spi.IRolePersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort iUserServicePort;
    private final IUserResponseMapper iUserResponseMapper;
    private final IUserRequestMapper iUserRequestMapper;
    private final IRolePersistencePort iRolePersistencePort;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        User user = iUserRequestMapper.userRequestToUser(userRequest);

        Role role = iRolePersistencePort.getRoleById(2L);
        user.setRole(role);

        User registerUser = iUserServicePort.registerUser(user);

        return iUserResponseMapper.toUserResponseDto(registerUser);
    }
}
