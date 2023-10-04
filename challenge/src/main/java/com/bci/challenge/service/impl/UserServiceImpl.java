package com.bci.challenge.service.impl;

import java.util.Date;;
import java.util.Optional;

import com.bci.challenge.dto.TokenRequest;
import com.bci.challenge.dto.UserCompleteDto;
import com.bci.challenge.dto.UserDto;
import com.bci.challenge.dto.UserRequest;
import com.bci.challenge.entities.User;
import com.bci.challenge.exception.EmailException;
import com.bci.challenge.exception.PasswordException;
import com.bci.challenge.exception.TokenException;
import com.bci.challenge.exception.UserAlreadyExistsException;
import com.bci.challenge.mappers.UserMapper;
import com.bci.challenge.repository.IUserRepository;
import com.bci.challenge.security.JwtProvider;
import com.bci.challenge.service.IUserService;
import com.bci.challenge.utils.ConstantesGenerales;
import com.bci.challenge.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
    IUserRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
    JwtProvider jwtProvider;

	UserMapper mapper = new UserMapper();

	@Override
	public UserDto create(UserRequest userRequest) {
		UserDto response = new UserDto();
		if (!Utils.validaEmail(userRequest.getEmail())) {
			throw new EmailException(ConstantesGenerales.ERROR_FORMATO_EMAIL, ConstantesGenerales.ERROR_FORMATO_EMAIL_CODE);
		}
		if (!Utils.validaContrasena(userRequest.getPassword())) {
			throw new PasswordException(ConstantesGenerales.ERROR_PASSWORD, ConstantesGenerales.ERROR_PASSWORD_CODE);
		}
		Optional<User> usuarioOptional = repository.findByEmail(userRequest.getEmail());
		if (!usuarioOptional.isPresent()) {
			User user = new User();
			userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
			user = mapper.userMapper(userRequest);
			user.setToken(jwtProvider.createToken(user));
			response = mapper.fromEntityToDto(repository.save(user));
		} else {
			//si el email existe pero tiene el token expirado le genera uno nuevo y sino lanza una exception
			if (!jwtProvider.validate(usuarioOptional.get().getToken())) {
				throw new UserAlreadyExistsException(ConstantesGenerales.ERROR_USUARIO_EXISTENTE,
						ConstantesGenerales.ERROR_USUARIO_EXISTENTE_CODE);
			}
			usuarioOptional.get().setToken(jwtProvider.createToken(usuarioOptional.get()));
			response = mapper.fromEntityToDto(repository.save(usuarioOptional.get()));
		}
		return response;
	}

	@Override
	public UserCompleteDto login(TokenRequest token) {
		Optional<User> userOptional = repository.findByToken(token.getToken());
		if (!userOptional.isPresent()) {
			throw new TokenException(ConstantesGenerales.BAD_TOKEN, ConstantesGenerales.BAD_TOKEN_CODE);
		}
		UserCompleteDto userDto = mapper.fromEntityToCompleteDto(userOptional.get());
		//si el usuario existe verifica que el token no haya expirado
		if (!jwtProvider.validate(token.getToken())) {
			throw new TokenException(ConstantesGenerales.TOKEN_EXPIRED, ConstantesGenerales.TOKEN_EXPIRED_CODE);
		}
		//si el usuario existe y el token no expiró se realiza un update de atributo lastLogin con la fecha actual
		User user = mapper.fromCompleteDtoToEntity(userDto);
		user.setLastLogin(new Date());
		repository.save(user);
		return userDto;
	}
}
