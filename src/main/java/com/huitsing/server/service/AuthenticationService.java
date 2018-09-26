package com.huitsing.server.service;

import com.huitsing.server.model.auth.AuthResponseModel;
import com.huitsing.server.model.auth.LoginReqeustModel;
import com.huitsing.server.model.auth.SignUpRequestModel;

public interface AuthenticationService {
	public AuthResponseModel authenticate(LoginReqeustModel model) throws Exception;
	public AuthResponseModel register(SignUpRequestModel model) throws Exception;
}
