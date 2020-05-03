package com.ems.backend;

import com.ems.backend.model.security.EmsRole;
import com.ems.backend.model.security.Role;
import com.ems.backend.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class EmsBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmsBackendApplication.class, args);
    }

    private RoleRepository roleRepository;

    public EmsBackendApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = Role.builder().name(EmsRole.ROLE_USER).build();
        Role admin = Role.builder().name(EmsRole.ROLE_ADMIN).build();
        Role groupAdmin = Role.builder().name(EmsRole.ROLE_GROUP_ADMIN).build();
        Role instructor = Role.builder().name(EmsRole.ROLE_INSTRUCTOR).build();
        Role mod = Role.builder().name(EmsRole.ROLE_MODERATOR).build();
        List<Role> roles = Arrays.asList(userRole, admin, groupAdmin, instructor, mod);
//        this.roleRepository.deleteAll();
        log.info("Insert roles to Mongo repository");
        long count = roles.stream()
                .filter(role -> {
                    if (!this.roleRepository.existsByName(role.getName())) {
                        this.roleRepository.save(role);
                        return true;
                    }
                    return false;
                })
                .count();
        log.info("Roles inserted in to Mongo repository {}", count);
//        if (this.roleRepository.existsByName())
//        this.roleRepository.saveAll(Arrays.asList(userRole, admin, groupAdmin, instructor, mod));
    }
}
