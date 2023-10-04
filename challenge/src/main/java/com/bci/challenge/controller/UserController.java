package com.bci.challenge.controller;

import com.bci.challenge.dto.TokenRequest;
import com.bci.challenge.dto.UserDto;
import com.bci.challenge.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.challenge.service.IUserService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService service;
	
	@PostMapping("/sign-up")
	@JsonFormat
	public UserDto signUp(@RequestBody UserRequest userRequest) {
		return service.create(userRequest);
	}
	
	@PostMapping("/login")
	@JsonFormat
	public UserDto login(@RequestBody TokenRequest token) {
		return service.login(token);
	}

}
