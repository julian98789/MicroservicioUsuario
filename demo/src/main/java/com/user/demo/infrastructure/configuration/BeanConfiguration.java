package com.user.demo.infrastructure.configuration;


import com.user.demo.domain.api.IUserServicePort;
import com.user.demo.domain.spi.IRolePersistencePort;
import com.user.demo.domain.spi.IUserPersistencePort;
import com.user.demo.domain.usecase.UserUseCase;
import com.user.demo.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.user.demo.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.user.demo.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.user.demo.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.user.demo.infrastructure.output.jpa.repository.IRoleRepository;
import com.user.demo.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
   private final IRoleRepository iRoleRepository;
   private final IRoleEntityMapper iRoleEntityMapper;

    @Bean
    public IRolePersistencePort iRolePersistencePort() {
        return new RoleJpaAdapter(iRoleRepository,iRoleEntityMapper);
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(userRepository,userEntityMapper);
    }

    @Bean
    public IUserServicePort iUserServicePort(IUserPersistencePort iUserPersistencePort) {
        return new UserUseCase(iUserPersistencePort);
    }
}
