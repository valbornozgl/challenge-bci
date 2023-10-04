package com.bci.challenge.service;

import com.bci.challenge.dto.TokenRequest;
import com.bci.challenge.dto.UserDto;
import com.bci.challenge.dto.UserRequest;
import com.bci.challenge.exception.EmailException;
import com.bci.challenge.exception.PasswordException;
import com.bci.challenge.exception.TokenException;
import com.bci.challenge.exception.UserAlreadyExistsException;

public interface IUserService {
	
	UserDto create(UserRequest userRequest);
	
	UserDto login(TokenRequest token);

}
