package com.huitsing.server.service;

import com.huitsing.server.model.auth.AuthResponseModel;
import com.huitsing.server.model.auth.LoginReqeustModel;

public interface AuthenticationService {
	public AuthResponseModel authenticate(LoginReqeustModel model) throws Exception;
}
