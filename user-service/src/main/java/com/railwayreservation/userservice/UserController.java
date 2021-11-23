package com.railwayreservation.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.railwayreservation.userservice.model.BookingList;
import com.railwayreservation.userservice.model.BookingModel;
import com.railwayreservation.userservice.model.TrainList;
import com.railwayreservation.userservice.model.TrainModel;
import com.railwayreservation.userservice.model.UserModel;
import com.railwayreservation.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * @GetMapping("/") public String welcome() { return
	 * "Here you can access all user's functionalities"; }
	 */
	
	@PostMapping("/bookTicket")
	public String bookTicket(@RequestBody BookingModel bookingDetails) {
		return userService.bookTicket(bookingDetails);
	}

	@GetMapping("/checkPNR/{pnr}")
	public BookingModel checkPNRstatus(@PathVariable("pnr") String pnr) {
		return userService.checkPNRstatus(pnr);
	}

	@GetMapping("/showBookedTickets/{uname}")
	public BookingList showBookedTickets(@PathVariable("uname") String uname) {
		return userService.showBookedTickets(uname);
	}
	
	@DeleteMapping("/cancelTicket/{pnr}")
	public String cancelTicket(@PathVariable("pnr") String pnr) {
		return userService.cancelTicket(pnr);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserModel loginDetails) {
		return userService.login(loginDetails);
	}
	
	@PostMapping("/register/user")
	public String registerUser(@RequestBody UserModel userDetails) {
		return userService.registerUser(userDetails);
	}
	
	@PostMapping("/searchTrains")
	public TrainList searchTrains(@RequestBody TrainModel train) {
		return userService.searchTrains(train);
	}
	

}
