package com.bci.challenge.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = -123609995092455047L;
	
	private String message;
	private Integer code;

	public UserAlreadyExistsException(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

}
