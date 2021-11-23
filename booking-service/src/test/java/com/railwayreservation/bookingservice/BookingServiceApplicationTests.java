package com.railwayreservation.bookingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.railwayreservation.bookingservice.model.BookingModel;
import com.railwayreservation.bookingservice.repository.BookingRepository;
import com.railwayreservation.bookingservice.service.BookingService;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingServiceApplicationTests {

	@MockBean
	private BookingRepository bookingRepository;

	@MockBean
	private BookingService bookingService;

	@Autowired
	private BookingController bookingController;

	@Test
	public void checkPNRstatusTest() {
		String pnr = "adn3456wer";
		when(bookingRepository.findBookingByPNR(pnr))
				.thenReturn(new BookingModel("adn3456wer", "Nikhil Singh", 22, "nikhil313311@gmail.com", "male",
						"Dehradun", "Delhi", "NandaDeviExpress", 54, "27/10/21", "general", 540.00, "Confirmed"));

		BookingModel booking = bookingRepository.findBookingByPNR(pnr);

		assertEquals("adn3456wer", booking.getPnr());
		assertEquals("Nikhil Singh", booking.getPassengerName());
		assertEquals("male", booking.getPassengerGender());
		assertEquals("nikhil313311@gmail.com", booking.getEmail());
		assertEquals(540.00, booking.getCost());
		assertEquals("Confirmed", booking.getStatus());
		assertEquals("general", booking.getQuota());
		assertEquals(22, booking.getPassengerAge());
		assertEquals("27/10/21", booking.getDate());
		assertEquals("Dehradun", booking.getFromStation());
		assertEquals("Delhi", booking.getToStation());
		assertEquals("NandaDeviExpress", booking.getTrainName());
		assertEquals(54, booking.getTrainId());

	}

	@Test
	public void showBookedTicketsTest() {
		String name = "Nikhil Singh";
		when(bookingRepository.findBookingByPassengerName(name)).thenReturn(Stream
				.of(new BookingModel("adn3456wer", "Nikhil Singh", 22, "nikhil313311@gmail.com", "male", "Dehradun",
						"Delhi", "NandaDeviExpress", 54, "27/10/21", "general", 540.00, "Confirmed"))
				.collect(Collectors.toList()));

		List<BookingModel> booking = bookingRepository.findBookingByPassengerName(name);

		assertEquals("adn3456wer", booking.get(0).getPnr());
		assertEquals("Nikhil Singh", booking.get(0).getPassengerName());
		assertEquals("male", booking.get(0).getPassengerGender());
		assertEquals("nikhil313311@gmail.com", booking.get(0).getEmail());
		assertEquals(540.00, booking.get(0).getCost());
		assertEquals("Confirmed", booking.get(0).getStatus());
		assertEquals("general", booking.get(0).getQuota());
		assertEquals(22, booking.get(0).getPassengerAge());
		assertEquals("27/10/21", booking.get(0).getDate());
		assertEquals("Dehradun", booking.get(0).getFromStation());
		assertEquals("Delhi", booking.get(0).getToStation());
		assertEquals("NandaDeviExpress", booking.get(0).getTrainName());
		assertEquals(54, booking.get(0).getTrainId());

	}

	@Test
	public void bookTicketTest() {
		BookingModel booking = new BookingModel("and4722ikh", "Nikhil Singh", 22, "nikhil313311@gmail.com", "male",
				"Dehradun", "Delhi", "NandaDeviExpress", 54, "27/10/21", "general", 540.00, "Confirmed");
		when(bookingService.bookTicket(booking))
				.thenReturn("Ticket booked successfully, your PNR number is and4722ikh , and your status is Confirmed");
		assertEquals("Ticket booked successfully, your PNR number is and4722ikh , and your status is Confirmed",
				bookingController.bookTicket(booking));
	}

	@Test
	public void cancelTicketTest() {
		when(bookingService.cancelTicket("adn3456wer")).thenReturn("Ticket cancelled Successfully");
		assertEquals("Ticket cancelled Successfully", bookingController.cancelTicket("adn3456wer"));
	}

}