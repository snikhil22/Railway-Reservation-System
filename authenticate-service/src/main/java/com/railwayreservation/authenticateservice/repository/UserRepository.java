package com.railwayreservation.authenticateservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.railwayreservation.authenticateservice.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {

	@Query(value = "{userName:?0}")
	public UserModel findUserByUserName(String userName);

	@Query(value = "{email:?0}")
	public UserModel findUserByEmail(String email);

}
