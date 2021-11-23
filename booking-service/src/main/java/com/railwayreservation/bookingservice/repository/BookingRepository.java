package com.railwayreservation.bookingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.railwayreservation.bookingservice.model.BookingModel;

public interface BookingRepository extends MongoRepository<BookingModel, String> {

	@Query(value = "{pnr:?0}")
	public BookingModel findBookingByPNR(String pnr);

	@Query(value = "{passengerName:?0}")
	public List<BookingModel> findBookingByPassengerName(String passengerName);

	@Query(value = "{trainName:?0,fromStation:?1,toStation:?2}")
	public List<BookingModel> findBookingByTrainNameAndStations(String trainName, String fromStation, String toStation);

	@Query(value = "{trainName:?0,fromStation:?1,toStation:?2,date:?3,quota:?4}")
	public List<BookingModel> findBookingByTrainNameStationsDateQuota(String trainName, String fromStation,
			String toStation, String date, String quota);

}
