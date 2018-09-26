package com.huitsing.server.service;

import com.huitsing.server.model.HelloModel;
import com.huitsing.server.model.response.OperationResponse;

public interface HelloService {
	public HelloModel getHello(Integer helloId) throws Exception;
	public OperationResponse createHello(HelloModel helloModel) throws Exception;
	public OperationResponse updateHello(HelloModel helloModel, Integer helloId) throws Exception;
	public OperationResponse deleteHello(Integer helloId) throws Exception;
}
