package com.tccfinal.app.ws.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tccfinal.app.ws.io.model.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
