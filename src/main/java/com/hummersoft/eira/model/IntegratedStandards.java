package com.hummersoft.eira.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mparameterintegratedstandards")
public class IntegratedStandards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "integratedid")
    private BigInteger IntegratedId;
    
    @Column(name = "dataloggerid")
    private BigInteger DataLoggerId;
    
    @Column(name = "parametername")
    private String ParameterName;
    
    @Column(name = "parameteruom")
    private String ParameterUom;
    
    @Column(name = "standardname")
    private String StandardName;
    
    @Column(name = "activeflag")
    private Integer ActiveFlag;
    
    @Column(name = "creationdate")
    private Timestamp CreationDate;
    
    @Column(name = "createdby")
    private BigInteger CreatedBy;
    
    @Column(name = "lastupdateddate")
    private Timestamp LastUpdatedDate;
    
    @Column(name = "lastupdatedby")
    private BigInteger LastUpdatedBy;
    
    @Column(name = "sequenceid")
    private BigInteger SequenceId;
    
    @Column(name = "coefficient")
    private BigDecimal CoEfficient;

	public BigInteger getIntegratedId() {
		return IntegratedId;
	}

	public void setIntegratedId(BigInteger integratedId) {
		IntegratedId = integratedId;
	}

	public BigInteger getDataLoggerId() {
		return DataLoggerId;
	}

	public void setDataLoggerId(BigInteger dataLoggerId) {
		DataLoggerId = dataLoggerId;
	}

	public String getParameterName() {
		return ParameterName;
	}

	public void setParameterName(String parameterName) {
		ParameterName = parameterName;
	}

	public String getParameterUom() {
		return ParameterUom;
	}

	public void setParameterUom(String parameterUom) {
		ParameterUom = parameterUom;
	}

	public String getStandardName() {
		return StandardName;
	}

	public void setStandardName(String standardName) {
		StandardName = standardName;
	}

	public Integer getActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		ActiveFlag = activeFlag;
	}

	public Timestamp getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		CreationDate = creationDate;
	}

	public BigInteger getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		CreatedBy = createdBy;
	}

	public Timestamp getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public BigInteger getLastUpdatedBy() {
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}

	public BigInteger getSequenceId() {
		return SequenceId;
	}

	public void setSequenceId(BigInteger sequenceId) {
		SequenceId = sequenceId;
	}

	public BigDecimal getCoEfficient() {
		return CoEfficient;
	}

	public void setCoEfficient(BigDecimal coEfficient) {
		CoEfficient = coEfficient;
	}
    
      

}
