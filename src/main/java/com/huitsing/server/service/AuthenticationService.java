package com.huitsing.server.service;

import com.huitsing.server.model.auth.AuthModel;
import com.huitsing.server.model.auth.LoginModel;

public interface AuthenticationService {
	public AuthModel authenticate(LoginModel model) throws Exception;
}
