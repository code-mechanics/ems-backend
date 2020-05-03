package com.ems.backend.repository;

import com.ems.backend.model.security.EmsRole;
import com.ems.backend.model.security.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(EmsRole name);
    boolean existsByName(EmsRole name);
}
