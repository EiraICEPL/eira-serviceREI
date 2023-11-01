package com.hummersoft.eira.dto;

import java.util.List;

import com.hummersoft.eira.dto.EventDTO;

public class SiteViewDTO {
	
	private Integer SiteID;
	private String siteName;
	
	private String siteCode;
	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	private Integer EquipmentCount;
	private Integer inverterCount;
	private Integer energymeterCount;
	
	private String TodayEvents;
	private String TotalEvents;
	
	public Integer getEquipmentCount() {
		return EquipmentCount;
	}

	public void setEquipmentCount(Integer equipmentCount) {
		EquipmentCount = equipmentCount;
	}

	private String Capacity;
	private Double InstallationCapacity;
	private String InvertersCapacity;
	private String EquipmentsCapacity;
	private String AnnualYield;
	
	private String Co;
	private String TotalCo2;
	
	private String TotalEnergy;
	private String TodayEnergy;
	private String ProductionDate;
	private String EnergyLastUpdate;

	private String TotalProductionYeild;
	private String TodayProductionYeild;
	private String YesterdayProductionYeild;

	private String TotalPerformanceRatio;
	private String TodayPerformanceRatio;
	private String YesterdayPerformanceRatio;
	
	private String LoadFactor;
	private String cuf;
	
	public String getCuf() {
		return cuf;
	}

	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	private List<EquipmentListDTO> equipmentList;
	
	private List<EventDTO> eventList;
	
	public List<EquipmentListDTO> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentListDTO> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getAnnualYield() {
		return AnnualYield;
	}

	public void setAnnualYield(String annualYield) {
		AnnualYield = annualYield;
	}

	public Integer getSiteID() {
		return SiteID;
	}

	public void setSiteID(Integer siteID) {
		SiteID = siteID;
	}

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

	public Double getInstallationCapacity() {
		return InstallationCapacity;
	}

	public void setInstallationCapacity(Double installationCapacity) {
		InstallationCapacity = installationCapacity;
	}

	public String getInvertersCapacity() {
		return InvertersCapacity;
	}

	public void setInvertersCapacity(String invertersCapacity) {
		InvertersCapacity = invertersCapacity;
	}

	public String getEquipmentsCapacity() {
		return EquipmentsCapacity;
	}

	public void setEquipmentsCapacity(String equipmentsCapacity) {
		EquipmentsCapacity = equipmentsCapacity;
	}

	public String getTotalEquipment() {
		return TotalEquipment;
	}

	public void setTotalEquipment(String totalEquipment) {
		TotalEquipment = totalEquipment;
	}

	private String TotalEquipment;

	public Integer getInverterCount() {
		return inverterCount;
	}

	public void setInverterCount(Integer inverterCount) {
		this.inverterCount = inverterCount;
	}

	public Integer getEnergymeterCount() {
		return energymeterCount;
	}

	public void setEnergymeterCount(Integer energymeterCount) {
		this.energymeterCount = energymeterCount;
	}

	public String getCo() {
		return Co;
	}

	public void setCo(String co) {
		Co = co;
	}

	public String getTotalCo2() {
		return TotalCo2;
	}

	public void setTotalCo2(String totalCo) {
		TotalCo2 = totalCo;
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

	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String productionDate) {
		ProductionDate = productionDate;
	}

	public String getEnergyLastUpdate() {
		return EnergyLastUpdate;
	}

	public void setEnergyLastUpdate(String energyLastUpdate) {
		EnergyLastUpdate = energyLastUpdate;
	}

	public String getTotalProductionYeild() {
		return TotalProductionYeild;
	}

	public void setTotalProductionYeild(String totalProductionYeild) {
		TotalProductionYeild = totalProductionYeild;
	}

	public String getTodayProductionYeild() {
		return TodayProductionYeild;
	}

	public void setTodayProductionYeild(String todayProductionYeild) {
		TodayProductionYeild = todayProductionYeild;
	}

	public String getYesterdayProductionYeild() {
		return YesterdayProductionYeild;
	}

	public void setYesterdayProductionYeild(String yesterdayProductionYeild) {
		YesterdayProductionYeild = yesterdayProductionYeild;
	}

	public String getTotalPerformanceRatio() {
		return TotalPerformanceRatio;
	}

	public void setTotalPerformanceRatio(String totalPerformanceRatio) {
		TotalPerformanceRatio = totalPerformanceRatio;
	}

	public String getTodayPerformanceRatio() {
		return TodayPerformanceRatio;
	}

	public void setTodayPerformanceRatio(String todayPerformanceRatio) {
		TodayPerformanceRatio = todayPerformanceRatio;
	}

	public String getYesterdayPerformanceRatio() {
		return YesterdayPerformanceRatio;
	}

	public void setYesterdayPerformanceRatio(String yesterdayPerformanceRatio) {
		YesterdayPerformanceRatio = yesterdayPerformanceRatio;
	}

	public String getLoadFactor() {
		return LoadFactor;
	}

	public void setLoadFactor(String loadFactor) {
		LoadFactor = loadFactor;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getTodayEvents() {
		return TodayEvents;
	}

	public void setTodayEvents(String todayEvents) {
		TodayEvents = todayEvents;
	}

	public String getTotalEvents() {
		return TotalEvents;
	}

	public void setTotalEvents(String totalEvents) {
		TotalEvents = totalEvents;
	}

	public List<EventDTO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDTO> eventList) {
		this.eventList = eventList;
	}

}

