package com.github.user.manager.security.constant.validator;


import com.github.user.manager.security.constant.UniqueUsername;
import com.github.user.manager.security.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 石少东
 * @date 2020-06-16 17:41
 */

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final IUserRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !repository.existsByUsername(username);
    }

}
