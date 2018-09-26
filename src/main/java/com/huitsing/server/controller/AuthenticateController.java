package com.huitsing.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huitsing.server.model.auth.AuthResponseModel;
import com.huitsing.server.model.auth.LoginReqeustModel;
import com.huitsing.server.model.auth.SignUpRequestModel;
import com.huitsing.server.model.response.OperationResponse;
import com.huitsing.server.service.AuthenticationService;
import com.huitsing.server.validator.LoginModelValidator;


@RestController
@RequestMapping("/authenticate/")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private LoginModelValidator loginModelValidator;
	
	@RequestMapping(value = "login", method = RequestMethod.PUT)
	public ResponseEntity<AuthResponseModel> login(@RequestBody LoginReqeustModel loginModel, BindingResult result) {
		AuthResponseModel response = null;
		loginModelValidator.validate(loginModel, result);
		if(result.hasErrors()) {
			response = new AuthResponseModel();
			response.setResponse(OperationResponse.generateFailedResponse(result.getAllErrors().toString()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			response = authenticationService.authenticate(loginModel);
		} catch (Exception e) {
			e.printStackTrace();
			response = new AuthResponseModel();
			response.setResponse(OperationResponse.generateFailedResponse(e.getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(response.getResponse().isFailed()) {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResponseEntity<AuthResponseModel> signUp(@RequestBody SignUpRequestModel model, BindingResult result) {
		AuthResponseModel response = null;
//		loginModelValidator.validate(loginModel, result);
//		if(result.hasErrors()) {
//			response = new AuthResponseModel();
//			response.setResponse(OperationResponse.generateFailedResponse(result.getAllErrors().toString()));
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
		try {
			response = authenticationService.register(model);
		} catch (Exception e) {
			e.printStackTrace();
			response = new AuthResponseModel();
			response.setResponse(OperationResponse.generateFailedResponse(e.getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
