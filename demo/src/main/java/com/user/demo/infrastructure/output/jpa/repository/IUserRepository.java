package com.user.demo.infrastructure.output.jpa.repository;

import com.user.demo.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByIdentification(String identification);
    Optional<UserEntity> findByEmail(String findByEmail);
    Optional<UserEntity> findByName(String name);
}
