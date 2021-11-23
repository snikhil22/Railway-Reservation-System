package com.railwayreservation.traininfoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayreservation.traininfoservice.model.TrainList;
import com.railwayreservation.traininfoservice.model.TrainModel;
import com.railwayreservation.traininfoservice.repository.TrainRepository;

@Service
public class TrainInfoService {
	
	@Autowired
	private TrainRepository trainRepository;
	
	public String addTrain(TrainModel train) {
		trainRepository.save(train);
		return "Train added successfully!!!";
	}

	public TrainList searchTrains(TrainModel train) {
		return new TrainList(trainRepository.findTrainByStations(train.getFromStation(),train.getToStation()));
	}

	public String updateTrainFare(TrainModel train) {
		TrainModel savedTrain= trainRepository.findTrainByNameAndStations(train.getTrainName(),train.getFromStation(),train.getToStation());
		savedTrain.setFareGeneral(train.getFareGeneral());
		savedTrain.setFareLadies(train.getFareLadies());
		trainRepository.save(savedTrain);
		return "Train Fare Updated";
	}

	public String deleteTrain(String trainName) {
		trainRepository.deleteAllTrainsByName(trainName);
		return "Train removed successfully";
	}
	
	

}
