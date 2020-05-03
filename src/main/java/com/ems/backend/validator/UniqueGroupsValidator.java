package com.ems.backend.validator;

import com.ems.backend.annotation.UniqueUsers;
import com.ems.backend.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class UniqueGroupsValidator implements ConstraintValidator<UniqueUsers, List<String>> {

    private final GroupRepository groupRepository;
    private int min;

    @Override
    public void initialize(UniqueUsers constraintAnnotation) {
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
        List<String> collect = strings.stream()
                .filter(groupRepository::existsByGroupId)
                .collect(Collectors.toList());
        return collect.size() == strings.size();
    }
}
