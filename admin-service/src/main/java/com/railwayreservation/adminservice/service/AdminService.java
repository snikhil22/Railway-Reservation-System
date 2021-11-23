package com.railwayreservation.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.railwayreservation.adminservice.model.TrainModel;
import com.railwayreservation.adminservice.model.UserModel;

@Service
public class AdminService {
	
	@Autowired
	private RestTemplate restTemplate;


	public String addTrain(TrainModel train) {
		return restTemplate.postForObject("http://train-info-service/addTrain", train, String.class);
	}

	public String registerAdmin(UserModel adminDetails) {
		return restTemplate.postForObject("http://authenticate-service/register/admin", adminDetails, String.class);
	}

	public String login(UserModel loginDetails) {
		return restTemplate.postForObject("http://authenticate-service/login", loginDetails, String.class);
	}

	public String updateTrainFare(TrainModel train) {
		return restTemplate.postForObject("http://train-info-service/updateTrainFare/",train,String.class);
	}

	public String deleteTrain(String trainName) {
		return restTemplate.getForObject("http://train-info-service/deleteTrain/"+trainName,String.class);
	}

}
