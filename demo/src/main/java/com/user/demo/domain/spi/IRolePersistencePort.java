package com.user.demo.domain.spi;

import com.user.demo.domain.model.Role;

public interface IRolePersistencePort {
    Role getRoleById(Long id);
}
