package com.railwayreservation.adminservice.model;

public class TrainModel {

	private int trainId;
	private String trainName;
	private String fromStation;
	private String toStation;
	private int totalSeatsLadies;
	private int totalSeatsGeneral;
	private Double fareLadies;
	private Double fareGeneral;

	public TrainModel() {

	}

	public TrainModel(int trainId, String trainName, String fromStation, String toStation, int totalSeatsLadies,
			int totalSeatsGeneral, Double fareLadies, Double fareGeneral) {
		super();
		this.trainId = trainId;
		this.trainName = trainName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.totalSeatsLadies = totalSeatsLadies;
		this.totalSeatsGeneral = totalSeatsGeneral;

		this.fareLadies = fareLadies;
		this.fareGeneral = fareGeneral;

	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
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

	public Double getFareLadies() {
		return fareLadies;
	}

	public void setFareLadies(Double fareLadies) {
		this.fareLadies = fareLadies;
	}

	public Double getFareGeneral() {
		return fareGeneral;
	}

	public void setFareGeneral(Double fareGeneral) {
		this.fareGeneral = fareGeneral;
	}

}
