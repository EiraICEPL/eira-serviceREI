package com.hummersoft.eira.dto;

import java.util.Date;
import java.util.List;

public class EquipmentViewDTO {
	
	private Integer siteID;
	private Integer equipmentID;
	private String equipmentCode;
	private String equipmentReference;
	private String equipmentName;
	private String equipmentType;
	private String customerNaming;
	private String customerReference;
	private String inverterEfficiency;
	private String networkStatus;
	private String todayEnergy;
	private String totalEnergy;
	private String performanceRatio;
	private String lastUpdate;
	private String activePower;
	private String disconnectType;
	private String disconnectRating;
	private String components;
	private String serialNumber;
	private String equipmentCategory;
	private String capacity;
	
	
	//Equipment History/Replacement details
	private String isPrimary;

	private String installationDate;

	private String warantryExpire;

	private String description, remarks;

	private Integer replacementCount;

	private Date lastReplacement;
	
	
	List<EventDTO> eventList;
	
	
	public Integer getSiteID() {
		return siteID;
	}
	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}
	public Integer getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(Integer equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getEquipmentReference() {
		return equipmentReference;
	}
	public void setEquipmentReference(String equipmentReference) {
		this.equipmentReference = equipmentReference;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getCustomerNaming() {
		return customerNaming;
	}
	public void setCustomerNaming(String customerNaming) {
		this.customerNaming = customerNaming;
	}
	public String getInverterEfficiency() {
		return inverterEfficiency;
	}
	public void setInverterEfficiency(String inverterEfficiency) {
		this.inverterEfficiency = inverterEfficiency;
	}
	public String getNetworkStatus() {
		return networkStatus;
	}
	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}
	public String getTodayEnergy() {
		return todayEnergy;
	}
	public void setTodayEnergy(String todayEnergy) {
		this.todayEnergy = todayEnergy;
	}
	public String getTotalEnergy() {
		return totalEnergy;
	}
	public void setTotalEnergy(String totalEnergy) {
		this.totalEnergy = totalEnergy;
	}
	public String getPerformanceRatio() {
		return performanceRatio;
	}
	public void setPerformanceRatio(String performanceRatio) {
		this.performanceRatio = performanceRatio;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getActivePower() {
		return activePower;
	}
	public void setActivePower(String activePower) {
		this.activePower = activePower;
	}
	public String getDisconnectType() {
		return disconnectType;
	}
	public void setDisconnectType(String disconnectType) {
		this.disconnectType = disconnectType;
	}
	public String getDisconnectRating() {
		return disconnectRating;
	}
	public void setDisconnectRating(String disconnectRating) {
		this.disconnectRating = disconnectRating;
	}
	public String getComponents() {
		return components;
	}
	public void setComponents(String components) {
		this.components = components;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getEquipmentCategory() {
		return equipmentCategory;
	}
	public void setEquipmentCategory(String equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getCustomerReference() {
		return customerReference;
	}
	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getInstallationDate() {
		return installationDate;
	}
	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}
	public String getWarantryExpire() {
		return warantryExpire;
	}
	public void setWarantryExpire(String warantryExpire) {
		this.warantryExpire = warantryExpire;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getReplacementCount() {
		return replacementCount;
	}
	public void setReplacementCount(Integer replacementCount) {
		this.replacementCount = replacementCount;
	}
	public Date getLastReplacement() {
		return lastReplacement;
	}
	public void setLastReplacement(Date lastReplacement) {
		this.lastReplacement = lastReplacement;
	}
	public List<EventDTO> getEventList() {
		return eventList;
	}
	public void setEventList(List<EventDTO> eventList) {
		this.eventList = eventList;
	}
	
}

