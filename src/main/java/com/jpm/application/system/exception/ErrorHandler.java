package com.jpm.application.system.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is for handling all errors thrown
 * 
 * @author Mindtree Ltd.
 *
 */

@Service
public class ErrorHandler {
	@Autowired
	private Environment env;
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);





	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorObject processServletRequestBindingExceptions(ServletRequestBindingException ex) {
		LOGGER.error("ServletRequestBindingException error : ", ex);
		ErrorObject error = new ErrorObject(env.getProperty("REC"), ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return error;
	}





	@ExceptionHandler(RequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorObject processRequestException(RequestException ex) {
		LOGGER.error("Bad Request : ", ex);
		ErrorObject error = null;
		if (ex.getError() != null) {
			try {
				Error errorObj = ex.getError();
				String errorMessage = env.getProperty(errorObj.getCode());
				int splitPos = errorMessage.indexOf(':');
				error = new ErrorObject(errorMessage.substring(0, splitPos), errorObj.getReason(), errorMessage.substring(splitPos + 1));
			} catch (Exception t) {
				LOGGER.error("Internal Server Error : ", t);
				error = new ErrorObject(env.getProperty("REC"), ex.getMessage());
			}
		}
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return error;
	}





	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorObject processAuthorizationExceptions(AccessDeniedException ex) {
		LOGGER.error("Security error : ", ex);
		ErrorObject error = new ErrorObject(env.getProperty("SEC"), ex.getMessage());
		error.setStatus(HttpStatus.FORBIDDEN.value());
		return error;
	}





	@ExceptionHandler(DataAccessResourceFailureException.class)
	@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
	@ResponseBody
	public ErrorObject processDataAccessResourceFailureException(DataAccessResourceFailureException ex) {
		LOGGER.error("Connection time out error : ", ex);
		ErrorObject error = new ErrorObject(env.getProperty("GTO"), "Resource access timed out");
		error.setStatus(HttpStatus.GATEWAY_TIMEOUT.value());
		return error;
	}





	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public ErrorObject processServiceExceptions(ServiceException ex) {
		LOGGER.error("Service error : ", ex);
		ErrorObject error = new ErrorObject(env.getProperty("SER"), ex.getMessage());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		return error;
	}





	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorObject processAuthenticationExceptions(AuthenticationException ex) {
		LOGGER.error("Service error : ", ex);
		ErrorObject error = new ErrorObject(env.getProperty("SEC"), ex.getMessage());
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		return error;
	}





	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorObject processAllException(Exception ex) {
		LOGGER.error("Internal Server Error : ", ex);
		Throwable cause = ex.getCause();
		if (cause == null) {
			cause = ex;
		}

		String reason = "";
		if (cause.getMessage() != null && !cause.getMessage().isEmpty()) {
			reason = cause.getMessage();
		}
		String errorProp = env.getProperty("ISE");
		int splitPos = errorProp.indexOf(':');
		ErrorObject error = new ErrorObject(errorProp.substring(0, splitPos), reason, errorProp.substring(splitPos + 1));
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return error;
	}
}