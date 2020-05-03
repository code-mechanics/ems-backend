package com.ems.backend.annotation;

import com.ems.backend.model.security.EmsRole;
import com.ems.backend.validator.UniqueUsersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsersValidator.class)
public @interface UniqueUsers {
    String message() default "{User is not unique}"; //we can also give prop key here com.sms.backend.user.unique.message
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    EmsRole[] userType();
    int min() default 0;
}
