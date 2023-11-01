package com.hummersoft.eira.dto;

public interface SiteEnergyDtlDTO {
	
	String getProductionOn();
	String getLastChecked();
	String getLastDataReceived();
	String getLastDownTime();
	String getTotalEnergy();
	String getTodayEnergy();
	String getTodayHoursOn();
	String getCo2();
	String getTotalCo2();

}
