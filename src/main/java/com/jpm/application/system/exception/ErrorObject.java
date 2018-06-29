package com.jpm.application.system.exception;

import java.time.Instant;

import lombok.Data;

/**
 * Error object carries a code, technical reason and a message for end user.
 * 
 * @author Mindtree Ltd.
 *
 */
@Data
public class ErrorObject {
	private String code;
	private String error;
	private int status;
	private String message;
	private long timestamp;





	public ErrorObject(String code, String reason, String message) {
		super();
		this.code = code;
		this.error = reason;
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
	}





	public ErrorObject(String codeAndReason, String message) {
		super();
		String[] codeReason = codeAndReason.split(":");
		this.code = codeReason[0];
		this.error = codeReason[1];
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
	}
}
