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
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
   private final IRoleRepository iRoleRepository;
   private final IRoleEntityMapper iRoleEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IRolePersistencePort iRolePersistencePort() {
        return new RoleJpaAdapter(iRoleRepository,iRoleEntityMapper);
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(userRepository,userEntityMapper,passwordEncoder);
    }

    @Bean
    public IUserServicePort iUserServicePort(IUserPersistencePort iUserPersistencePort) {
        return new UserUseCase(iUserPersistencePort);
    }

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Documentation")
                        .description("API Documentation with JWT Authentication")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")));
    }
}


