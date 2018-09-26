package com.huitsing.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huitsing.server.dao.HelloDAO;
import com.huitsing.server.entity.Hello;
import com.huitsing.server.model.HelloModel;
import com.huitsing.server.model.response.OperationResponse;
import com.huitsing.server.service.HelloService;
@Service
@Transactional
public class HelloServiceImpl implements HelloService {
	
	@Autowired
	private HelloDAO helloDAO;

	@Override
	public HelloModel getHello(Integer helloId) throws Exception {
		HelloModel model = new HelloModel();
		Hello hello = helloDAO.findById(helloId);
		if(hello == null) {
			throw new Exception("Hello is not valid.");
		}
		model.setTitle(hello.getContent());
		return model;
	}

	@Override
	public OperationResponse createHello(HelloModel helloModel) throws Exception {
		Hello hello = new Hello();
		hello.setContent(helloModel.getContent());
		hello.setTitle(helloModel.getTitle());
		helloDAO.save(hello);
		return OperationResponse.generateSuccessResponse();
	}

	@Override
	public OperationResponse updateHello(HelloModel helloModel, Integer helloId) throws Exception {
		Hello hello = helloDAO.findById(helloId);
		if(hello == null) {
			throw new Exception("Cannot find this entity.");
		}
		hello.setContent(helloModel.getContent());
		hello.setTitle(helloModel.getTitle());
		helloDAO.save(hello);
		return OperationResponse.generateSuccessResponse();
	}

	@Override
	public OperationResponse deleteHello(Integer helloId) throws Exception {
		Hello hello = helloDAO.findById(helloId);
		if(hello == null) {
			throw new Exception("Cannot find this entity.");
		}
		helloDAO.delete(hello);
		return OperationResponse.generateSuccessResponse();
	}
}
