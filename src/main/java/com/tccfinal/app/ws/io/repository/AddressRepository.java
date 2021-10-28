package com.tccfinal.app.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tccfinal.app.ws.io.model.AddressEntity;
import com.tccfinal.app.ws.io.model.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
	
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
}
