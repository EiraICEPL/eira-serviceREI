package com.hummersoft.eira.dto;

public  class SiteStatDTO {
	
	
	
	/*
	 * private String TotalSites; private String RoofTopSites; private String
	 * UtilitySites; private String OfflineStatus; private String ActiveStatus;
	 * private String WarningStatus; private String DownStatus;
	 */
	 
	public String getSiteType() {
		return SiteType;
	}
	public void setSiteType(String siteType) {
		SiteType = siteType;
	}
	public int getRoofTopSites() {
		return RoofTopSites;
	}
	public void setRoofTopSites(int roofTopSites) {
		RoofTopSites = roofTopSites;
	}
	public int getUtilitySites() {
		return UtilitySites;
	}
	public void setUtilitySites(int utilitySites) {
		UtilitySites = utilitySites;
	}
	public int getOfflineStatus() {
		return OfflineStatus;
	}
	public void setOfflineStatus(int offlineStatus) {
		OfflineStatus = offlineStatus;
	}
	public int getActiveStatus() {
		return ActiveStatus;
	}
	public void setActiveStatus(int activeStatus) {
		ActiveStatus = activeStatus;
	}
	public int getWarningStatus() {
		return WarningStatus;
	}
	public void setWarningStatus(int warningStatus) {
		WarningStatus = warningStatus;
	}
	public int getDownStatus() {
		return DownStatus;
	}
	public void setDownStatus(int downStatus) {
		DownStatus = downStatus;
	}
	String SiteType;
	int RoofTopSites;
	int UtilitySites;
	int OfflineStatus;
	int ActiveStatus;
	int WarningStatus;
	int DownStatus;
	
	
	
}
