package com.hummersoft.eira.dto;

public class EquipmentSpecificYieldDTO {
	
	private Double todayEnergy;
	private String timeStamp;
	private Double specificYield;
	private int equipmentid;
	private String equipmentName;

	

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public int getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(int equipmentid) {
		this.equipmentid = equipmentid;
	}

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
