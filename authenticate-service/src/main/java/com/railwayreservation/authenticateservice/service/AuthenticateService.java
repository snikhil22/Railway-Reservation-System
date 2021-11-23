package com.railwayreservation.authenticateservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayreservation.authenticateservice.model.UserModel;
import com.railwayreservation.authenticateservice.repository.UserRepository;

@Service
public class AuthenticateService {

	@Autowired
	private UserRepository userRepo;

	public String registerAdmin(UserModel adminDetails) {
		adminDetails.setRole("Admin");
		return this.register(adminDetails);
	}

	public String registerUser(UserModel userDetails) {
		userDetails.setRole("User");
		return this.register(userDetails);
	}

	public String register(UserModel userDetails) {
		if (userRepo.findUserByEmail(userDetails.getEmail()) != null)
			return "You have already registered";
		else if (userRepo.findUserByUserName(userDetails.getUserName()) != null)
			return "Username not available";
		else {
			userRepo.save(userDetails);
			return "You are registered successfully";
		}

	}

	public String login(UserModel loginDetails) {

		if (userRepo.findUserByUserName(loginDetails.getUserName()) == null)
			return "No user with given UserName Present!!!";
		else {
			UserModel savedDetails = new UserModel(userRepo.findUserByUserName(loginDetails.getUserName()));
			if (savedDetails.getPassword().equals(loginDetails.getPassword()))
				return "Welcome " + savedDetails.getRole();
			else
				return "Wrong Password!!!";
		}
	}

}
