package com.jpm.application.system.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception to report all request errors.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestException extends RuntimeException {
	private static final long serialVersionUID = 1617896099203132932L;
	private final Error error;
	private final List<Error> errors;





	public RequestException() {
		this.error = null;
		this.errors = new ArrayList<Error>();
	}





	public RequestException(String code, String reason) {
		super(reason);
		this.error = new Error(code, reason);
		this.errors = new ArrayList<Error>();
	}





	public RequestException(List<Error> errors) {
		this.error = null;
		this.errors = errors;
	}





	public RequestException(String code, Throwable cause, String reason) {
		super(reason, cause);
		this.error = new Error(code, reason);
		this.errors = new ArrayList<Error>();
	}





	public void addErrors(String errCode, String reason) {
		this.errors.add(new Error(errCode, reason));
	}
}