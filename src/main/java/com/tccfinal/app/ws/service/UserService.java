package com.tccfinal.app.ws.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tccfinal.app.ws.io.model.AddressEntity;
import com.tccfinal.app.ws.io.model.UserEntity;
import com.tccfinal.app.ws.io.repository.AddressRepository;
import com.tccfinal.app.ws.io.repository.UserRepository;
import com.tccfinal.app.ws.service.resource.UserServiceResource;
import com.tccfinal.app.ws.shared.dto.UserDto;
import com.tccfinal.app.ws.shared.utils.Utils;

@Service
public class UserService implements UserServiceResource {

	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final ModelMapper MAPPER = new ModelMapper();
	
	public List<UserDto> getUsers(int page, int limit) {
		
		if(page > 0) page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> usersEntity = userPage.getContent();
		
		Type userTypeList = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> returnValue = MAPPER.map(usersEntity, userTypeList);
		
		return returnValue;
	}
	
	public UserDto getUserByUserId(String userId) {

		UserEntity returnValue = userRepository.findByUserId(userId);
		if (returnValue == null)
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");
		
		return MAPPER.map(returnValue, UserDto.class);
	}

	public UserDto createUser(UserDto user) {
		
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Record already exists");
		
		UserEntity returnValue = MAPPER.map(user, UserEntity.class);
		for(int i = 0; i < returnValue.getAddresses().size(); i++) {
			AddressEntity address = returnValue.getAddresses().get(i);
			address.setUserDetails(returnValue);
			address.setAddressId(utils.generateAddressId(30));
			returnValue.getAddresses().set(i, address);
		}
		
		returnValue.setUserId(utils.generateUserId(35));
		returnValue.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		returnValue = userRepository.save(returnValue);

		return MAPPER.map(returnValue, UserDto.class);
	}

	public UserDto updateUser(UserDto userDto, String userId) {
		
		UserEntity findValue = userRepository.findByUserId(userId);
		if (findValue == null)
			throw new UsernameNotFoundException("User with ID:" + userId + " not found");

		findValue.setFirstName(userDto.getFirstName());
		findValue.setLastName(userDto.getLastName());

		UserEntity returnValue = userRepository.save(findValue);

		return MAPPER.map(returnValue, UserDto.class);
	}

	public void deleteUser(String userId) {
		
		UserEntity deleteValue = userRepository.findByUserId(userId);
		if (deleteValue == null)
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");

		userRepository.delete(deleteValue);
	}

	public UserDto getUserByEmail(String email) {
		
		UserEntity findValue = userRepository.findByEmail(email);
		if (findValue == null)throw new UsernameNotFoundException("Username: " + email);
		
		List<AddressEntity> addresses = addressRepository.findAllByUserDetails(findValue);
		for(int index = 0; index < addresses.size(); index++) {
			addresses.get(index).setUserDetails(findValue);
		}
		findValue.setAddresses(addresses);
		
		UserDto returnValue =  MAPPER.map(findValue, UserDto.class);
		
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity findValue = userRepository.findByEmail(email);
		
		if (findValue == null)
			throw new UsernameNotFoundException("Username:" + email);
		
		List<AddressEntity> addresses = addressRepository.findAllByUserDetails(findValue);
		for(int index = 0; index < addresses.size(); index++) {
			addresses.get(index).setUserDetails(findValue);
		}
		findValue.setAddresses(addresses);

		return new User(findValue.getEmail(), findValue.getEncryptedPassword(),
				  (Collection<? extends GrantedAuthority>) new ArrayList<>());
	}
	
	public UserDto getUsersAddresses(String userId){
		UserDto findValue = getUserByUserId(userId);
		
		if(findValue == null)throw new UsernameNotFoundException("UserId: "+userId+" not found");
		//List<AddressDTO> addressesEntity = userService.getAddressesByUserDetails();
		return new UserDto();
	}
}












