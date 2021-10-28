package com.tccfinal.app.ws.service.resource;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tccfinal.app.ws.shared.dto.UserDto;

public interface UserServiceResource extends UserDetailsService{

	List<UserDto> getUsers(int page, int limit);
	UserDto createUser(UserDto userDto);
	UserDto getUserByEmail(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(UserDto userDto, String userId);
	void deleteUser(String userId);
	public UserDto getUsersAddresses(String userId);
}
