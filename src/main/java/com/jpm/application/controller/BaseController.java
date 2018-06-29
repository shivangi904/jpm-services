package com.jpm.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.service.BaseService;


@RestController
@RequestMapping(value = "/v1")
public class BaseController {
	/**
	 * {@link BaseService} instance
	 */
	
	@Autowired
	private BaseService userService;

	@RequestMapping(method = RequestMethod.POST, value="/factorial")
	@ResponseBody
	ResultResponse generateFactorial(@RequestBody UserInput userInput) {
		return userService.getFactorial(userInput);
	}

}
