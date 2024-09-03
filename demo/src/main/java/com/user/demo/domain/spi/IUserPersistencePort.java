package com.user.demo.domain.spi;

import com.user.demo.domain.model.User;

public interface IUserPersistencePort {

    User registerUser(User user);
    boolean existsByIdentification(String identification);
    boolean existsByEmail(String email);
}
