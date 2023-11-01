package com.hummersoft.eira.model;

import java.math.BigInteger;
import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "msitemap")
public class SiteMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapid", nullable = false)
    private Integer mapId;

    @Column(name = "activeflag")
    private Integer activeFlag;

    @Column(name = "createdby")
    private BigInteger createdBy;

    @Column(name = "creationdate")
    private Timestamp creationDate;

    @Column(name = "customerid")
    private BigInteger customerId;

    @Column(name = "lastupdatedby")
    private BigInteger lastUpdatedBy;

    @Column(name = "lastupdateddate")
    private Timestamp lastUpdatedDate;

    @Column(name = "siteid")
    private BigInteger siteId;

    @Column(name = "userid")
    private Integer userId;
   
	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}



	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public BigInteger getCustomerId() {
		return customerId;
	}

	public void setCustomerId(BigInteger customerId) {
		this.customerId = customerId;
	}

	public BigInteger getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public BigInteger getSiteId() {
		return siteId;
	}

	public void setSiteId(BigInteger siteId) {
		this.siteId = siteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    
}
