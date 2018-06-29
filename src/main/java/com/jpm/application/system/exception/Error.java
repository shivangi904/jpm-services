package com.jpm.application.system.exception;

import java.io.Serializable;

import lombok.Data;

/**
 * An object to carry error code and a technical reason
 * 
 * @author Mindtree Ltd.
 *
 */
@Data
public class Error implements Serializable {
	private static final long serialVersionUID = 154591516502289401L;
	private final String code;
	private final String reason;





	public Error(String code, String reason) {
		this.code = code;
		this.reason = reason;
	}
}