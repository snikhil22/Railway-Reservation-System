package com.railwayreservation.bookingservice.model;

import java.util.List;

public class BookingList {

	private List<BookingModel> list;

	public BookingList() {

	}

	public BookingList(List<BookingModel> list) {
		super();
		this.list = list;
	}

	public List<BookingModel> getList() {
		return list;
	}

	public void setList(List<BookingModel> list) {
		this.list = list;
	}

}
