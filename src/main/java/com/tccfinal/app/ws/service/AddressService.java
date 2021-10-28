package com.tccfinal.app.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccfinal.app.ws.io.model.UserEntity;
import com.tccfinal.app.ws.io.repository.AddressRepository;
import com.tccfinal.app.ws.service.resource.AddressServiceResource;
import com.tccfinal.app.ws.shared.dto.AddressDTO;

@Service
public class AddressService implements AddressServiceResource {
	
	@Autowired
	AddressRepository addressRepository;

	public List<AddressDTO> getAddressByUser(UserEntity userEntity) {
		
		return null;
	}

}















