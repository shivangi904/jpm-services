package com.jpm.application.service.impl;

import org.springframework.stereotype.Service;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.service.BaseService;
import com.jpm.application.system.exception.RequestException;

@Service("baseServiceImpl")
public class BaseServiceImpl implements BaseService {

	@Override
	public ResultResponse getFactorial(UserInput userInput) throws RequestException {
		
		//program to be written here
		
		int fact = userInput.getInputNumber();
		ResultResponse resultResponse = new ResultResponse();
		resultResponse.setResponseValue(fact);
		return resultResponse;
		
	}

}
