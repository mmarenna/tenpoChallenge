package com.tenpo.adder.user.repository;

import com.tenpo.adder.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    String ROLE_USER = "ROLE_USER";

    Optional<Role> findByName(String name);
}
