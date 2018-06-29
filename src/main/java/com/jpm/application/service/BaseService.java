package com.jpm.application.service;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.system.exception.RequestException;

public interface BaseService {

	ResultResponse getFactorial(UserInput userInput) throws RequestException;


}
