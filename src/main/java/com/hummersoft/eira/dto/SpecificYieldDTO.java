package com.hummersoft.eira.dto;

public class SpecificYieldDTO {
	
//
//	Double getTodayEnergy();
//
//	String getTimestamp();
//	Double setSpecificYield(Double specificYield);

	
	private Double todayEnergy;
	private String timeStamp;
	private Double specificYield;

	public Double getSpecificYield() {
		return specificYield;
	}

	public void setSpecificYield(Double specificYield) {
		this.specificYield = specificYield;
	}

	public Double getTodayEnergy() {
		return todayEnergy;
	}

	public void setTodayEnergy(Double todayEnergy) {
		this.todayEnergy = todayEnergy;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	

}
