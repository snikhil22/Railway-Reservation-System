package com.railwayreservation.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.railwayreservation.userservice.model.BookingList;
import com.railwayreservation.userservice.model.BookingModel;
import com.railwayreservation.userservice.model.TrainList;
import com.railwayreservation.userservice.model.TrainModel;
import com.railwayreservation.userservice.model.UserModel;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;

	public String bookTicket(BookingModel bookingDetails) {
		return restTemplate.postForObject("http://booking-service/bookTicket", bookingDetails, String.class);
	}

	
	 @HystrixCommand(fallbackMethod = "showBookedTickets_fallback", commandProperties = {
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5") })
	public BookingList showBookedTickets(String uname) {
		return restTemplate.getForObject("http://booking-service/showBookedTickets/" + uname,BookingList.class);
	}
	 
	 public BookingModel showBookedTickets_fallback(String custId) {
	    	System.out.println("Sorry for the inconvenience. Can't fetch your booked tickets right now, try again later.");
	    	return null;
	    	}

    @HystrixCommand(fallbackMethod = "checkPNR_fallback", commandProperties = {
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
	@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5") })
	public BookingModel checkPNRstatus(String pnr) {
		return restTemplate.getForObject("http://booking-service/checkPNR/"+pnr,BookingModel.class);
	}

    public BookingModel checkPNR_fallback(String custId) {
    	System.out.println("Sorry for the inconvenience. Can't fetch your pnr status right now, try again later.");
    	return null;
    	}
    
    
	public String cancelTicket(String pnr) {	
		return restTemplate.getForObject("http://booking-service/cancelTicket/" + pnr,String.class);
	}

	public String login(UserModel loginDetails) {
		return restTemplate.postForObject("http://authenticate-service/login", loginDetails, String.class);
	}

	public String registerUser(UserModel userDetails) {
		return restTemplate.postForObject("http://authenticate-service/register/user", userDetails, String.class);
	}

	public TrainList searchTrains(TrainModel train) {
		return restTemplate.postForObject("http://train-info-service/searchTrains",train, TrainList.class);
	}
	

}
