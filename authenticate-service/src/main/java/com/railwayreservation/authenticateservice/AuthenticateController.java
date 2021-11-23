package com.railwayreservation.authenticateservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railwayreservation.authenticateservice.model.UserModel;
import com.railwayreservation.authenticateservice.service.AuthenticateService;

@RestController
@RequestMapping("/")
public class AuthenticateController {

	@Autowired
	private AuthenticateService authService;

	/*
	 * @GetMapping("/") public String welcome() { return
	 * "Here you can login and register a new user"; }
	 */

	@PostMapping("/register/admin")
	public String registerAdmin(@RequestBody UserModel adminDetails) {
		return authService.registerAdmin(adminDetails);
	}

	@PostMapping("/register/user")
	public String registerUser(@RequestBody UserModel userDetails) {
		return authService.registerUser(userDetails);
	}

	@PostMapping("/login") 
	public String login(@RequestBody UserModel loginDetails) {
		return authService.login(loginDetails);
	}
	 

}
