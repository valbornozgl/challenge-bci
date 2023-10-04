package com.bci.challenge.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bci.challenge.dto.PhoneRequest;
import com.bci.challenge.dto.UserRequest;
import com.bci.challenge.entities.Phone;
import org.dozer.DozerBeanMapper;

import com.bci.challenge.dto.UserCompleteDto;
import com.bci.challenge.dto.UserDto;
import com.bci.challenge.entities.User;

public class UserMapper {
	
	DozerBeanMapper mapper = new DozerBeanMapper();

	  public List<UserDto> fromListEntityToListDto(List<User> users) {
	    List<UserDto> dtoList = new ArrayList<UserDto>();
	    for (User user : users) {
	      dtoList.add(mapper.map(user, UserDto.class));
	    }
	    return dtoList;
	  }

	  public UserDto fromEntityToDto(User user) {
	    if (user != null) {
	      return mapper.map(user, UserDto.class);
	    }
	    return null;
	  }

	  public User fromDtoToEntity(UserDto user) {
	    return mapper.map(user, User.class);
	  }

	  public UserCompleteDto fromEntityToCompleteDto(User user) {
	    if (user != null) {
	      return mapper.map(user, UserCompleteDto.class);
	    }
	    return null;
	  }

	  public User fromCompleteDtoToEntity(UserCompleteDto userDto) {
	    if (userDto != null) {
	      return mapper.map(userDto, User.class);
	    }
	    return null;
	  }

	public User userMapper(UserRequest userRequest) {
		return new User(null, UUID.randomUUID().toString().replace("-", ""), userRequest.getName(),
				userRequest.getEmail(), userRequest.getPassword(), new Date(), new Date(), "", true,
				phonesMapper(userRequest.getPhones()));
	}

	public List<Phone> phonesMapper(List<PhoneRequest> phones) {

		List<Phone> phonesList = new ArrayList<>();
		phones.stream().forEach(phone -> {
			Phone phoneAux = new Phone();
			phoneAux.setCityCode(phone.getCityCode());
			phoneAux.setCountryCode(phone.getCountryCode());
			phoneAux.setNumber(phone.getNumber());
			phonesList.add(phoneAux);
		});
		return phonesList;
	}

}
