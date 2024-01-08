package com.web.framework.model.request.validation;

import org.springframework.stereotype.Component;

import com.web.framework.model.request.SigninRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
@Component
public class SigninRequestValidator implements ConstraintValidator<EitherEmailOrUnameRequired, SigninRequest> {

	@Override
	public boolean isValid(SigninRequest value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

//    @Override
//    public boolean isValid(SigninRequest value, ConstraintValidatorContext context) {
//        return !(value.getEmail() == null && value.getUname() == null) && !(value.getEmail() != null && value.getUname() != null);
//    }
}
