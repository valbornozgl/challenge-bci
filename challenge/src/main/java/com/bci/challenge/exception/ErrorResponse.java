package com.bci.challenge.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private Integer codigo;
	private String detail;
	
	public ErrorResponse(Integer codigo, String detail) {
		super();
		this.timestamp = LocalDateTime.now();
		this.codigo = codigo;
		this.detail = detail;
	}

}
