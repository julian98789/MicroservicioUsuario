package com.user.demo.application.handler.user_microservice_handler;

import com.user.demo.application.dto.user_microservice_dto.userdto.UserRequest;
import com.user.demo.application.dto.user_microservice_dto.userdto.UserResponse;

public interface IUserHandler {
    UserResponse registerUser (UserRequest userRequest);
}
