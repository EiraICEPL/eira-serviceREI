package com.hummersoft.eira.dto;

public interface SiteListDTO {
	
	 Integer getSiteId();
	  String getSiteCode();
	  String getSiteReference();
	  String getSiteName();
	  String getLatitude();
	  String getLongitude();
	  String getAddress();
	  String getCity();
	  String getState();
	  String getCountry();
	  String getPostalCode();
	  String getActiveInverterCount();
	  String getInverterCount();
	  String getLastChecked();
	  Double getTotalEnergy();
	  Double getTodayEnergy();
	  String getLastDownTime();
	  String getLastDataReceived();
	  String getTodayHoursOn();
	  String getStatus();
	  Double getInstallationCapacity();
	  String getMobile();
	  String getTelephone();
	  String getLocationUrl();
	  String getSitetype();
	  
	  

}
