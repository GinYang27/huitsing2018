package com.huitsing.server.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huitsing.server.constant.WebsiteConstant;
import com.huitsing.server.dao.UserDAO;
import com.huitsing.server.dao.UserDetailDAO;
import com.huitsing.server.entity.User;
import com.huitsing.server.entity.UserDetail;
import com.huitsing.server.model.auth.AuthResponseModel;
import com.huitsing.server.model.auth.LoginReqeustModel;
import com.huitsing.server.model.auth.SignUpRequestModel;
import com.huitsing.server.model.response.OperationResponse;
import com.huitsing.server.service.AuthenticationService;
import com.huitsing.server.util.TimestampHelper;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDetailDAO userDetailDAO;
	
	@Override
	public AuthResponseModel authenticate(LoginReqeustModel loginModel) throws Exception{
		AuthResponseModel authModel = new AuthResponseModel();
		User user = userDAO.findByEmail(loginModel.getEmail().toLowerCase().trim());
		if(user == null) {
			authModel.setResponse(OperationResponse.generateFailedResponse("Email is incorret."));
			return authModel;
		}
		if(user.getPassword() == null) {
			throw new Exception("This user doesn't have passowrd in database.");
		}
		if(!user.getPassword().equals(loginModel.getPassword())) {
			authModel.setResponse(OperationResponse.generateFailedResponse("Password is incorrect."));
			return authModel;
		}
		
		String token = issueToken(user);
		authModel.setToken(token);
		authModel.setUserId(user.getId());
		authModel.setResponse(OperationResponse.generateSuccessResponse());
		return authModel;
	}
	
	private String issueToken(User user) {
		String token = UUID.randomUUID().toString();
		Timestamp expiredDate = TimestampHelper.getExpirationDate(WebsiteConstant.TOKEN_VALID_DAYS);
		user.setToken(token);
		user.setTokenExpirationDate(expiredDate);
		userDAO.save(user);
		return token;
	}

	@Override
	public AuthResponseModel register(SignUpRequestModel model) throws Exception {
		AuthResponseModel authModel = new AuthResponseModel();
		User user = userDAO.findByEmail(model.getEmail().toLowerCase().trim());
		if(user != null) {
			authModel.setResponse(OperationResponse.generateFailedResponse("The email is already registered"));
			return authModel;
		}
		UserDetail userDetail = saveUserDetail(model);
		user = new User();
		user.setEmail(model.getEmail());
		user.setPassword(model.getPassword());
		user.setUserDetail(userDetail);
		
		String token = issueToken(user);
		authModel.setToken(token);
		authModel.setUserId(user.getId());
		authModel.setResponse(OperationResponse.generateSuccessResponse());
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private UserDetail saveUserDetail(SignUpRequestModel model) throws Exception {
		UserDetail userDetail = new UserDetail();
		userDetail.setFirstName(model.getFirstName());
		userDetail.setLastName(model.getLastName());
		userDetailDAO.save(userDetail);
		return userDetail;
	}

}
