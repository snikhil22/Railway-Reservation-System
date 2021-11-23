package com.railwayreservation.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.railwayreservation.adminservice.model.TrainModel;
import com.railwayreservation.adminservice.model.UserModel;
import com.railwayreservation.adminservice.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/addTrain")
	public String addTrain(@RequestBody TrainModel train) {
		return adminService.addTrain(train);
	}

	@PostMapping("/register/admin")
	public String registerAdmin(@RequestBody UserModel adminDetails) {
		return adminService.registerAdmin(adminDetails);
	}
	
	@PostMapping("/updateTrainFare")
	public String updateTrainFare(@RequestBody TrainModel train) {
		return adminService.updateTrainFare(train);
	}
	
	@DeleteMapping("/deleteTrain/{trainName}")
	public String deleteTrain(@PathVariable("trainName") String trainName) {
		return adminService.deleteTrain(trainName);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserModel loginDetails) {
		return adminService.login(loginDetails);
	}

}
