package com.railwayreservation.traininfoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.railwayreservation.traininfoservice.model.TrainList;
import com.railwayreservation.traininfoservice.model.TrainModel;
import com.railwayreservation.traininfoservice.service.TrainInfoService;

@RestController
public class TrainInfoController {

	@Autowired
	private TrainInfoService trainInfoService;

	@PostMapping("/addTrain")
	public String addTrain(@RequestBody TrainModel train) {
		return trainInfoService.addTrain(train);
	}

	@PostMapping("/searchTrains")
	public TrainList searchTrains(@RequestBody TrainModel train) {
		return trainInfoService.searchTrains(train);
	}

	@PostMapping("/updateTrainFare")
	public String updateTrainFare(@RequestBody TrainModel train) {
		return trainInfoService.updateTrainFare(train);
	}
	
	@GetMapping("/deleteTrain/{trainName}")
	public String deleteTrain(@PathVariable("trainName") String trainName) {
		return trainInfoService.deleteTrain(trainName);
	}

}
