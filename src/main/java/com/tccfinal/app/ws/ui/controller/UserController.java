package com.tccfinal.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tccfinal.app.ws.service.UserService;
import com.tccfinal.app.ws.service.exception.UserServiceException;
import com.tccfinal.app.ws.shared.dto.UserDto;
import com.tccfinal.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tccfinal.app.ws.ui.model.response.AddressRest;
import com.tccfinal.app.ws.ui.model.response.ErrorMessages;
import com.tccfinal.app.ws.ui.model.response.OperationStatusModel;
import com.tccfinal.app.ws.ui.model.response.RequestOperationName;
import com.tccfinal.app.ws.ui.model.response.RequestOperationStatus;
import com.tccfinal.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	UserService userService;
	
	private final ModelMapper MAPPER = new ModelMapper();

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<UserRest> returnValue = new ArrayList<>();
		List<UserDto> usersDto = userService.getUsers(page, limit);

		for (UserDto userDto : usersDto)
			returnValue.add(MAPPER.map(userDto, UserRest.class));
		return returnValue;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String userId) {

		UserDto findValue = userService.getUserByUserId(userId);
		UserRest returnValue = MAPPER.map(findValue, UserRest.class);
		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userRequest) throws Exception {
		
		if (userRequest == null)
			throw new NullPointerException("The object is null");

		if (userRequest.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto createUser = MAPPER.map(userRequest, UserDto.class);

		UserDto returnValue = userService.createUser(createUser);
		return MAPPER.map(returnValue, UserRest.class);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userRequest) {

		if (userRequest == null)
			throw new NullPointerException("The object is null");

		if (userRequest.getFirstName().isEmpty() || userRequest.getLastName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserDto returnValue = userService.updateUser(MAPPER.map(userRequest, UserDto.class), userId);
		return MAPPER.map(returnValue, UserRest.class);
	}

	@DeleteMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String userId) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(userId);
	
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(path = "/{userId}/addresses", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public List<AddressRest> getUsersAddresses(@PathVariable String userId){
		
		UserDto findValue = userService.getUserByUserId(userId);
		UserRest userRest = MAPPER.map(findValue, UserRest.class);
		
		List<AddressRest> returnValue = userRest.getAddresses();
		
		return returnValue;
	}
}


















