package com.railwayreservation.bookingservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.railwayreservation.bookingservice.model.TrainSeatsModel;

public interface TrainSeatsRepository extends MongoRepository<TrainSeatsModel, Integer> {

	@Query(value = "{trainId:?0,date:?1}")
	public TrainSeatsModel findTrainByIdAndDate(int trainId, String date);

}
