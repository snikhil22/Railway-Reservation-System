package com.railwayreservation.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
//@EnableSwagger2
public class BookingServiceApplication {

	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
