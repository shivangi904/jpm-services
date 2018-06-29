package com.jpm.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.service.impl.BaseServiceImpl;
import com.jpm.application.system.exception.ServiceException;



@RestController
@RequestMapping(value = "/v1")
public class BaseController {
		
	@Autowired
	private BaseServiceImpl baseServiceImpl;
	
	@RequestMapping(method = RequestMethod.POST, value="/factorial")
	@ResponseBody
	public ResultResponse generateFactorial(@RequestBody UserInput userInput) {
		try {
			return baseServiceImpl.getFactorial(userInput);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}


