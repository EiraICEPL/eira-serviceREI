package com.hummersoft.eira.dto;

public class siteEnergyStatDTO {
	
	String todayEnergy;
	String siteName;
	String siteId;
	public String getTodayEnergy() {
		return todayEnergy;
	}
	public void setTodayEnergy(String todayEnergy) {
		this.todayEnergy = todayEnergy;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

}
