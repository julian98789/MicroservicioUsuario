package com.user.demo.application.handler;

import com.user.demo.application.dto.userdto.UserRequest;
import com.user.demo.application.dto.userdto.UserResponse;

public interface IUserHandler {
    UserResponse registerUser (UserRequest userRequest);
}
