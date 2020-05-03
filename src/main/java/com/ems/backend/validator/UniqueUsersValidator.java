package com.ems.backend.validator;

import com.ems.backend.annotation.UniqueUsers;
import com.ems.backend.model.security.EmsRole;
import com.ems.backend.model.security.Role;
import com.ems.backend.model.security.User;
import com.ems.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class UniqueUsersValidator implements ConstraintValidator<UniqueUsers, List<String>> {

    private final UserRepository userRepository;
    private int min;
    private List<EmsRole> emsRoles;

    @Override
    public void initialize(UniqueUsers constraintAnnotation) {
        emsRoles = Arrays.asList(constraintAnnotation.userType());
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        if (min > 0 && (strings == null || strings.size() != min)) {
            return false;
        }
        Set<String> collect1 = strings.stream().filter(i -> Collections.frequency(strings, i) > 1)
                .collect(Collectors.toSet());
        // If any duplicate element in list
        if (!CollectionUtils.isEmpty(collect1)) {
            return false;
        }
        //TODO : Try to check in mongodb if there any way to check all the user exist in DB in one call
        List<Boolean> collect = strings.stream()
                .map(userRepository::findByEmail)
                .map(this::checkRolesExist)
                .filter(BooleanUtils::isTrue)
                .collect(Collectors.toList());
        return collect.size() == strings.size();
    }

    private boolean checkRolesExist(Optional<User> user) {
        if (user.isPresent()) {
            User userVal = user.get();
            for (Role role:userVal.getRoles()) {
                if (!emsRoles.contains(role.getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
