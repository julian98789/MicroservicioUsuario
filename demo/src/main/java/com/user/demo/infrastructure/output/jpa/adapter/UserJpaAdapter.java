package com.user.demo.infrastructure.output.jpa.adapter;

import com.user.demo.domain.model.User;
import com.user.demo.domain.spi.IUserPersistencePort;
import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import com.user.demo.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User registerUser(User user) {

        UserEntity userEntity = userEntityMapper.toEntity(user);

        userEntity = userRepository.save(userEntity);

        return userEntityMapper.toModel(userEntity);
    }

    @Override
    public boolean existsByIdentification(String identification) {
        return userRepository.findByIdentification(identification).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
