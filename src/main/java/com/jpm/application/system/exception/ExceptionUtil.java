package com.jpm.application.system.exception;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Generates exception for the errors from BindingResult
 * 
 */
public class ExceptionUtil {

	private ExceptionUtil() {
	}





	public static void hasErrorsThrowException(BindingResult result, Logger logger) throws RequestException {
		if (result.hasErrors()) {
			RequestException exception = new RequestException();
			for (ObjectError error : result.getAllErrors()) {
				String msg = error.getDefaultMessage();
				exception.addErrors(error.getCode(), msg);
				logger.error("Error : " + msg);
			}
			throw exception;
		}
	}
}
