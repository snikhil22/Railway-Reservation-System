package com.railwayreservation.bookingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.railwayreservation.bookingservice.model.BookingList;
import com.railwayreservation.bookingservice.model.BookingModel;
import com.railwayreservation.bookingservice.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	/*
	 * @GetMapping("/") public String welcome() { return
	 * "Here you can book tickets, check PNR and previously booked tickets"; }
	 */

	@PostMapping("/bookTicket")
	public String bookTicket(@RequestBody BookingModel bookingDetails) {
		return bookingService.bookTicket(bookingDetails);
	}

	@GetMapping("/checkPNR/{pnr}")
	public BookingModel checkPNRstatus(@PathVariable("pnr") String pnr) {
		return bookingService.checkPNRstatus(pnr);
	}

	@GetMapping("/showBookedTickets/{passengerName}")
	public BookingList showBookedTickets(@PathVariable("passengerName") String passengerName) {
		return bookingService.showBookedTickets(passengerName);
	}

	@GetMapping("/cancelTicket/{pnr}")
	public String cancelTicket(@PathVariable("pnr") String pnr) {
		return bookingService.cancelTicket(pnr);
	}

}
