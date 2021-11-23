package com.railwayreservation.userservice.model;

public class BookingModel {

	private String pnr;
	private String status;
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	private String fromStation;
	private String toStation;
	private String trainName;
	private String email;
	private int trainNumber;
	private String date;
	private String quota;
	private Double cost;

	public BookingModel() {

	}

	public BookingModel(String pnr, String status, String passengerName, int passengerAge, String passengerGender,
			String fromStation, String toStation, String trainName, String email, int trainNumber, String date,
			String quota, Double cost) {
		super();
		this.pnr = pnr;
		this.email = email;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.trainName = trainName;
		this.trainNumber = trainNumber;
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

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
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
