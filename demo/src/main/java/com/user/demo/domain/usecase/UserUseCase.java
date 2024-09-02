package com.user.demo.domain.usecase;

import com.user.demo.domain.api.IUserServicePort;
import com.user.demo.domain.exception.EmailAlreadyExistsException;
import com.user.demo.domain.exception.IdentificationAlreadyExistsException;
import com.user.demo.domain.exception.UserNotOfLegalAge;
import com.user.demo.domain.model.User;
import com.user.demo.domain.spi.IUserPersistencePort;
import com.user.demo.domain.util.Util;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public User registerUser(User user) {
        validateUser(user);


        return iUserPersistencePort.registerUser(user);
    }

    private void validateUser(User user) {
        if (LocalDate.now().minusYears(18).isBefore(user.getDateOfBirth())) {
            throw new UserNotOfLegalAge(Util.USER_NOT_OF_LEGAL_EGE);
        }
        if (iUserPersistencePort.existsByIdentification(user.getIdentification())) {
            throw new IdentificationAlreadyExistsException(Util.USER_IDENTIFICATION_ALREADY_EXISTS);
        }
        if (iUserPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(Util.USER_EMAIL_ALREADY_EXISTS);
        }
    }

}
