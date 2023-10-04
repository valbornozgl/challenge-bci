package com.bci.challenge.dto;

import java.util.List;

import com.bci.challenge.entities.Phone;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserCompleteDto extends UserDto{
	
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;

}
