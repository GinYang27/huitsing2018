package com.huitsing.server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.huitsing.server.model.auth.LoginReqeustModel;

@Component
public class LoginModelValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginReqeustModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginReqeustModel loginModel = (LoginReqeustModel) target;

		if (loginModel == null) {
			errors.reject("Mode is null.");
			return;
		}

		if (loginModel.getEmail() == null || loginModel.getEmail().trim().isEmpty()) {
			errors.reject("Email is null.");
			return;
		}

		if (loginModel.getPassword() == null || loginModel.getPassword().trim().isEmpty()) {
			errors.reject("Password is null.");
			return;
		}

	}

}
