package com.hummersoft.eira.dto;

import org.springframework.stereotype.Component;

@Component
public class EnergyDetailsDTO {
	
	private String ProductionOn;
	private String LastChecked;
	private String LastDataReceived;
	private String LastDownTime;
	private String TotalEnergy;
	private String TodayEnergy;
	private String TodayHoursOn;
	private String Co2;
	private String TotalCo2;
	private String TodayCo2Avoided;
	private String	LoadFactor;
	private int totalEvents;
	private int todayEvents;
	
	private Double InstallationCapacity;
	
	
	public Double getInstallationCapacity() {
		return InstallationCapacity;
	}
	public void setInstallationCapacity(Double installationCapacity) {
		InstallationCapacity = installationCapacity;
	}
	public String getLoadFactor() {
		return LoadFactor;
	}
	public void setLoadFactor(String loadFactor) {
		LoadFactor = loadFactor;
	}
	public String getTodayCo2Avoided() {
		return TodayCo2Avoided;
	}
	public void setTodayCo2Avoided(String todayCo2Avoided) {
		TodayCo2Avoided = todayCo2Avoided;
	}
	public String getProductionOn() {
		return ProductionOn;
	}
	public void setProductionOn(String productionOn) {
		ProductionOn = productionOn;
	}
	public String getLastChecked() {
		return LastChecked;
	}
	public void setLastChecked(String lastChecked) {
		LastChecked = lastChecked;
	}
	public String getLastDataReceived() {
		return LastDataReceived;
	}
	public void setLastDataReceived(String lastDataReceived) {
		LastDataReceived = lastDataReceived;
	}
	public String getLastDownTime() {
		return LastDownTime;
	}
	public void setLastDownTime(String lastDownTime) {
		LastDownTime = lastDownTime;
	}
	public String getTotalEnergy() {
		return TotalEnergy;
	}
	public void setTotalEnergy(String totalEnergy) {
		TotalEnergy = totalEnergy;
	}
	public String getTodayEnergy() {
		return TodayEnergy;
	}
	public void setTodayEnergy(String todayEnergy) {
		TodayEnergy = todayEnergy;
	}
	public String getTodayHoursOn() {
		return TodayHoursOn;
	}
	public void setTodayHoursOn(String todayHoursOn) {
		TodayHoursOn = todayHoursOn;
	}
	public String getCo2() {
		return Co2;
	}
	public void setCo2(String co2) {
		Co2 = co2;
	}
	public String getTotalCo2() {
		return TotalCo2;
	}
	public void setTotalCo2(String totalCo2) {
		TotalCo2 = totalCo2;
	}
	public int getTotalEvents() {
		return totalEvents;
	}
	public void setTotalEvents(int totalEvents) {
		this.totalEvents = totalEvents;
	}
	public int getTodayEvents() {
		return todayEvents;
	}
	public void setTodayEvents(int todayEvents) {
		this.todayEvents = todayEvents;
	}

}
