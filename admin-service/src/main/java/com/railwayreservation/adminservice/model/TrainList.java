package com.railwayreservation.adminservice.model;

import java.util.List;

public class TrainList {
	private List<TrainModel> list;

	public TrainList() {

	}

	public TrainList(List<TrainModel> list) {
		super();
		this.list = list;
	}

	public List<TrainModel> getList() {
		return list;
	}

	public void setList(List<TrainModel> list) {
		this.list = list;
	}

}
