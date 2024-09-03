package com.user.demo.infrastructure.output.jpa.repository;

import com.user.demo.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {

}
