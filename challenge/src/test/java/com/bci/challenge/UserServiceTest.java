package com.bci.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bci.challenge.dto.PhoneRequest;
import com.bci.challenge.dto.UserDto;
import com.bci.challenge.dto.UserRequest;
import com.bci.challenge.dto.UserResponse;
import com.bci.challenge.repository.IUserRepository;
import com.bci.challenge.security.JwtProvider;
import com.bci.challenge.service.IUserService;
import com.bci.challenge.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bci.challenge.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = UserServiceTest.class)
public class UserServiceTest {

	@Mock
	private UserDto userDto;
	@Mock
	private User user;
	@Mock
	private UserRequest userRequest;
	@Mock
	private IUserRepository repository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private JwtProvider jwtProvider;
	@Mock
	List<PhoneRequest> phones;
	@Mock
	private UserResponse userResponse;
	@InjectMocks
	private UserServiceImpl service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserExitoso() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("vic@globallogic.com");
		userRequest.setName("Victor Albornoz");
		userRequest.setPassword("d1dsdSdsdsd2");
		user = Mockito.spy(new User());
		user.setEmail(userRequest.getEmail());
		user.setId(1L);
		user.setPassword(userRequest.getPassword());
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);
		when(repository.findByEmail(userRequest.getEmail())).thenReturn(Optional.empty());
		when(repository.save(user)).thenReturn(user);

		//Assert.assertNotNull(service.create(userRequest));
	}
	
	@Test
	public void testCreateUserWithError() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("julio@testssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("a2asfGfdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

	}

	@Test
	public void testCreateUserEmailIncorrecto() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("juliotestssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("a2asfGfdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		//Assert.assertEquals(ConstantesGenerales.ERROR_FORMATO_EMAIL, controller.signUp(userRequest).getError().getDetail());
	}

	@Test
	public void testCreateUserPassordInvalido() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("julio@testssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("aasffdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		//Assert.assertEquals(ConstantesGenerales.ERROR_PASSWORD, controller.signUp(userRequest).getError().getDetail());
	}
	
	private PhoneRequest fillPhones() {
		PhoneRequest phone = new PhoneRequest();
		phone.setCityCode(1);
		phone.setCountryCode("02");
		phone.setNumber(123456L);

		return phone;
	}

}
