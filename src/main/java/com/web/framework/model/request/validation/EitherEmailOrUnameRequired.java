package com.web.framework.model.request.validation;
import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SigninRequestValidator.class })
@Documented
public @interface EitherEmailOrUnameRequired {
    String message() default "signin.validation.eitherUsernameOrEmailMandatory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
