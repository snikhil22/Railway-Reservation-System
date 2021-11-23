package com.railwayreservation.bookingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bookings")
public class BookingModel {

	@Id
	private String pnr;
	private String status;
	private String passengerName;
	private int passengerAge;
	private String email;
	private String passengerGender;
	private String fromStation;
	private String toStation;
	private String trainName;
	private int trainId;
	private String date;
	private String quota;
	private Double cost;

	public BookingModel() {

	}

	public BookingModel(String pnr, String passengerName, int passengerAge, String email, String passengerGender,
			String fromStation, String toStation, String trainName, int trainId, String date, String quota, Double cost,
			String status) {
		super();
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.email = email;
		this.passengerGender = passengerGender;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.trainName = trainName;
		this.trainId = trainId;
		this.quota = quota;
		this.cost = cost;
		this.status = status;
		this.date = date;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
