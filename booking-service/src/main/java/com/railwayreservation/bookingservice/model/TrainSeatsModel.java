package com.railwayreservation.bookingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Train Seats Info")
public class TrainSeatsModel {

	@Id
	private int trainId;
	private String date;
	private int totalSeatsLadies;
	private int totalSeatsGeneral;
	private int leftSeatsLadies;
	private int leftSeatsGeneral;
	private int wlLadies;
	private int wlGeneral;

	public TrainSeatsModel(int trainId, String date, int totalSeatsLadies, int totalSeatsGeneral, int leftSeatsLadies,
			int leftSeatsGeneral, int wlLadies, int wlGeneral) {
		super();
		this.trainId = trainId;
		this.date = date;
		this.totalSeatsLadies = totalSeatsLadies;
		this.totalSeatsGeneral = totalSeatsGeneral;
		this.leftSeatsLadies = leftSeatsLadies;
		this.leftSeatsGeneral = leftSeatsGeneral;
		this.wlLadies = wlLadies;
		this.wlGeneral = wlGeneral;
	}

	public TrainSeatsModel() {
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalSeatsLadies() {
		return totalSeatsLadies;
	}

	public void setTotalSeatsLadies(int totalSeatsLadies) {
		this.totalSeatsLadies = totalSeatsLadies;
	}

	public int getTotalSeatsGeneral() {
		return totalSeatsGeneral;
	}

	public void setTotalSeatsGeneral(int totalSeatsGeneral) {
		this.totalSeatsGeneral = totalSeatsGeneral;
	}

	public int getLeftSeatsLadies() {
		return leftSeatsLadies;
	}

	public void setLeftSeatsLadies(int leftSeatsLadies) {
		this.leftSeatsLadies = leftSeatsLadies;
	}

	public int getLeftSeatsGeneral() {
		return leftSeatsGeneral;
	}

	public void setLeftSeatsGeneral(int leftSeatsGeneral) {
		this.leftSeatsGeneral = leftSeatsGeneral;
	}

	public int getWlLadies() {
		return wlLadies;
	}

	public void setWlLadies(int wlLadies) {
		this.wlLadies = wlLadies;
	}

	public int getWlGeneral() {
		return wlGeneral;
	}

	public void setWlGeneral(int wlGeneral) {
		this.wlGeneral = wlGeneral;
	}

}
