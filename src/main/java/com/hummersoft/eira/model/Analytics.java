package com.hummersoft.eira.model;

import java.io.Serializable;
import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tdatatransaction")
public class Analytics implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionid")
	private Integer TransactionId;

	@Column(name = "siteid")
	private Integer SiteId;

	@Column(name = "equipmentid")
	private Integer EquipmentId;

	@Column(name = "todayenergy")
	private Double TodayEnergy;

	@Column(name = "totalenergy")
	private Double TotalEnergy;

	@Column(name = "irradiation")
	private Double Irradiation;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "todayenergyflag")
	private int TodayEnergyFlag;
	
	@Transient
	private Double specificYield;
	
	public Double getSpecificYield() {
		return specificYield;
	}

	public void setSpecificYield(Double specificYield) {
		this.specificYield = specificYield;
	}

	public int getTodayEnergyFlag() {
		return TodayEnergyFlag;
	}

	public void setTodayEnergyFlag(int todayEnergyFlag) {
		TodayEnergyFlag = todayEnergyFlag;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public Integer getSiteId() {
		return SiteId;
	}

	public void setSiteId(Integer siteId) {
		SiteId = siteId;
	}

	public Integer getEquipmentId() {
		return EquipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		EquipmentId = equipmentId;
	}

	public Double getTodayEnergy() {
		return TodayEnergy;
	}

	public void setTodayEnergy(Double todayEnergy) {
		TodayEnergy = todayEnergy;
	}

	public Double getTotalEnergy() {
		return TotalEnergy;
	}

	public void setTotalEnergy(Double totalEnergy) {
		TotalEnergy = totalEnergy;
	}

	public Double getIrradiation() {
		return Irradiation;
	}

	public void setIrradiation(Double irradiation) {
		Irradiation = irradiation;
	}

}
