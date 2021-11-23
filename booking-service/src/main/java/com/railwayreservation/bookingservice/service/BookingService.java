package com.railwayreservation.bookingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.railwayreservation.bookingservice.model.BookingList;
import com.railwayreservation.bookingservice.model.BookingModel;
import com.railwayreservation.bookingservice.model.TrainModel;
import com.railwayreservation.bookingservice.model.TrainSeatsModel;
import com.railwayreservation.bookingservice.repository.BookingRepository;
import com.railwayreservation.bookingservice.repository.TrainRepository;
import com.railwayreservation.bookingservice.repository.TrainSeatsRepository;

@Service
public class BookingService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TrainSeatsRepository trainSeatsRepository;

	public String bookTicket(BookingModel bookingDetails) {

		if (trainRepository.findTrainByNameAndStations(bookingDetails.getTrainName(), bookingDetails.getFromStation(),
				bookingDetails.getToStation()) == null) {
			return "No such train found!!!";
		}

		else {

			TrainModel trainDetails = trainRepository.findTrainByNameAndStations(bookingDetails.getTrainName(),
					bookingDetails.getFromStation(), bookingDetails.getToStation());

			bookingDetails.setTrainId(trainDetails.getTrainId());

			if (trainSeatsRepository.findTrainByIdAndDate(bookingDetails.getTrainId(),
					bookingDetails.getDate()) == null) {

				TrainSeatsModel seatDetails = new TrainSeatsModel();
				seatDetails.setTrainId(trainDetails.getTrainId());
				seatDetails.setDate(bookingDetails.getDate());
				seatDetails.setTotalSeatsGeneral(trainDetails.getTotalSeatsGeneral());
				seatDetails.setTotalSeatsLadies(trainDetails.getTotalSeatsLadies());
				if (bookingDetails.getQuota().equalsIgnoreCase("general")) {
					seatDetails.setLeftSeatsGeneral(trainDetails.getTotalSeatsGeneral() - 1);
					seatDetails.setLeftSeatsLadies(trainDetails.getTotalSeatsLadies());
				} else {
					seatDetails.setLeftSeatsGeneral(trainDetails.getTotalSeatsGeneral());
					seatDetails.setLeftSeatsLadies(trainDetails.getTotalSeatsLadies() - 1);
				}
				seatDetails.setWlGeneral(0);
				seatDetails.setWlLadies(0);

				trainSeatsRepository.save(seatDetails);

				bookingDetails.setStatus("Confirmed");

			} else {

				TrainSeatsModel seatDetails = trainSeatsRepository.findTrainByIdAndDate(bookingDetails.getTrainId(),
						bookingDetails.getDate());

				if ((bookingDetails.getQuota().equalsIgnoreCase("general") && seatDetails.getWlGeneral() >= 5)
						|| (bookingDetails.getQuota().equalsIgnoreCase("ladies") && seatDetails.getWlLadies() >= 5))
					return "all seats are booked in this quota, try other quota or train";

				if (bookingDetails.getQuota().equalsIgnoreCase("ladies")) {

					int seats = seatDetails.getLeftSeatsLadies();

					if (seats < 1) {

						int wlseats = seatDetails.getWlLadies();
						wlseats += 1;
						seatDetails.setWlLadies(wlseats);
						bookingDetails.setStatus("W/L " + wlseats);
					} else {
						seatDetails.setLeftSeatsLadies(seats - 1);
						bookingDetails.setStatus("Confirmed");
					}
					trainSeatsRepository.save(seatDetails);
				}

				else {

					int seats = seatDetails.getLeftSeatsGeneral();

					if (seats < 1) {

						int wlseats = seatDetails.getWlGeneral();
						wlseats += 1;
						seatDetails.setWlGeneral(wlseats);
						bookingDetails.setStatus("W/L " + wlseats);
					} else {
						seatDetails.setLeftSeatsGeneral(seats - 1);
						bookingDetails.setStatus("Confirmed");
					}
					trainSeatsRepository.save(seatDetails);
				}

			}

			bookingDetails.setPnr(UUID.randomUUID().toString());

			if (bookingDetails.getQuota().equalsIgnoreCase("ladies"))
				bookingDetails.setCost(trainDetails.getFareLadies());
			else
				bookingDetails.setCost(trainDetails.getFareGeneral());

			bookingRepository.save(bookingDetails);

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(bookingDetails.getEmail());
			msg.setSubject("Ticket booked");
			msg.setText("Ticket booked successfully, your ticket details are :\n" + "PNR : " + bookingDetails.getPnr()
					+ "\nStatus : " + bookingDetails.getStatus());

			javaMailSender.send(msg);

			return "Ticket booked successfully, your PNR number is " + bookingDetails.getPnr()
					+ " , and your status is " + bookingDetails.getStatus();
		}

	}

	public BookingModel checkPNRstatus(String pnr) {
		return bookingRepository.findBookingByPNR(pnr);
	}

	public BookingList showBookedTickets(String passengerName) {
		return new BookingList(bookingRepository.findBookingByPassengerName(passengerName));
	}

	public String cancelTicket(String pnr) {
		if (bookingRepository.findBookingByPNR(pnr) == null)
			return "No such ticket available";
		else if (bookingRepository.findBookingByPNR(pnr).getStatus().equalsIgnoreCase("Cancelled"))
			return "Ticket is already Cancelled";
		else {

			BookingModel bookedTicket = bookingRepository.findBookingByPNR(pnr);

			List<BookingModel> list = new ArrayList<BookingModel>(bookingRepository
					.findBookingByTrainNameStationsDateQuota(bookedTicket.getTrainName(), bookedTicket.getFromStation(),
							bookedTicket.getToStation(), bookedTicket.getDate(), bookedTicket.getQuota()));

			if (bookedTicket.getStatus().equalsIgnoreCase("Confirmed")) {
				for (BookingModel ticket : list) {
					if (ticket.getStatus().equalsIgnoreCase("Confirmed") == false
							&& ticket.getStatus().equalsIgnoreCase("Cancelled") == false) {
						int i = Integer.parseInt(ticket.getStatus().substring(4));
						if (i == 1)
							ticket.setStatus("Confirmed");
						else
							ticket.setStatus("W/L " + (i - 1));

						bookingRepository.save(ticket);
					}
				}
			} else {
				int q = Integer.parseInt(bookedTicket.getStatus().substring(4));
				for (BookingModel ticket : list) {
					if (ticket.getStatus().equalsIgnoreCase("Confirmed") == false
							&& ticket.getStatus().equalsIgnoreCase("Cancelled") == false) {
						if (Integer.parseInt(ticket.getStatus().substring(4)) > q) {
							int i = Integer.parseInt(ticket.getStatus().substring(4));
							ticket.setStatus("W/L " + (i - 1));
							bookingRepository.save(ticket);
						}
					}
				}
			}

			TrainSeatsModel seatDetails = trainSeatsRepository.findTrainByIdAndDate(bookedTicket.getTrainId(),
					bookedTicket.getDate());
			if (bookedTicket.getQuota().equalsIgnoreCase("Ladies")) {
				if (seatDetails.getWlLadies() > 0) {
					int wl = seatDetails.getWlLadies();
					seatDetails.setWlLadies(wl - 1);
				} else {
					int totalSeatsLeft = seatDetails.getLeftSeatsLadies();
					seatDetails.setLeftSeatsLadies(totalSeatsLeft + 1);
				}
			} else {
				if (seatDetails.getWlGeneral() > 0) {
					int wl = seatDetails.getWlGeneral();
					seatDetails.setWlGeneral(wl - 1);
				} else {
					int totalSeatsLeft = seatDetails.getLeftSeatsGeneral();
					seatDetails.setLeftSeatsGeneral(totalSeatsLeft + 1);
				}
			}
			trainSeatsRepository.save(seatDetails);

			bookedTicket.setStatus("Cancelled");
			bookingRepository.save(bookedTicket);
			return "Ticket cancelled Successfully";
		}
	}

}
