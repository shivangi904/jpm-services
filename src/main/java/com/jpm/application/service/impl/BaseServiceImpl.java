package com.jpm.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.service.BaseService;
import com.jpm.application.system.exception.RequestException;

@Service("userService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	private ResultResponse resultResponse;
	
	
	@Override
	public ResultResponse getFactorial(UserInput userInput) throws RequestException {
		
		//program to be written here
		
		int fact = userInput.getInputNumber();
		resultResponse.setResponseValue(fact);
		return resultResponse;
		
	}

}
