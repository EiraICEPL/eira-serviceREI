package com.hummersoft.eira.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="mequipmenttype")
public class EquipmentType implements Serializable{

	 private static final long serialVersionUID = -723583058586873479L;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name = "equipmenttypeid")
	 private Integer EquipmentTypeId;
	 
	 @Column(name="equipmenttypecode")
	 private String EquipmentTypeCode;
	 
	 @Column(name="equipmenttype")
	 private String EquipmentType;
	 
	 @Column(name="categoryid")
	 private Integer CategoryID;
	 

	 @Transient
	 private String CategoryName;	
		
	/* @Column(name="PartID")
	 private Integer PartID;*/
	 
	 public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	@Column(name="description")
	 private String Description;
	 
	 @Column(name="displayname")
	 private String DisplayName;
	 
	/* @Column(name="ParameterVersion")
	 private Integer ParameterVersion;
	 
	 @Column(name="SOPVersion")
	 private Integer SOPVersion;*/
	 
	 @Column(name="remarks")
	 private String Remarks;
	 
	 @Column(name="customerreference")
	 private String CustomerReference;
	 
	 @Column(name="customernaming")
	 private String CustomerNaming;
	 
	 /*@Column(name="Capacity")
	 private float Capacity;
	 */
	
	 /* @Column(name="UOMID")
	 private Integer UOMID;
	 */
	 @Column(name="manufacturer")
	 private String Manufacturer;
	 
	 @Column(name="moduletype")
	 private String ModuleType;
	
 
	 @Column(name="activeflag")
	 private Integer ActiveFlag;

	 @Column(name="creationdate")
	 private Date CreationDate;

	 @Column(name="createdby")
	 private Integer CreatedBy;

	 @Column(name="lastupdateddate")
	 private Date LastUpdatedDate;

	 @Column(name="lastupdatedby")
	 private Integer LastUpdatedBy;

	public Integer getEquipmentTypeId() {
		return EquipmentTypeId;
	}

	public void setEquipmentTypeId(Integer equipmentTypeId) {
		EquipmentTypeId = equipmentTypeId;
	}

	public String getEquipmentTypeCode() {
		return EquipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		EquipmentTypeCode = equipmentTypeCode;
	}

	public String getEquipmentType() {
		return EquipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		EquipmentType = equipmentType;
	}

	public Integer getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(Integer categoryID) {
		CategoryID = categoryID;
	}

	/*public Integer getPartID() {
		return PartID;
	}

	public void setPartID(Integer partID) {
		PartID = partID;
	}*/

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDisplayName() {
		return DisplayName;
	}

	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}

	/*public Integer getParameterVersion() {
		return ParameterVersion;
	}

	public void setParameterVersion(Integer parameterVersion) {
		ParameterVersion = parameterVersion;
	}

	public Integer getSOPVersion() {
		return SOPVersion;
	}

	public void setSOPVersion(Integer sOPVersion) {
		SOPVersion = sOPVersion;
	}*/

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getCustomerReference() {
		return CustomerReference;
	}

	public void setCustomerReference(String customerReference) {
		CustomerReference = customerReference;
	}

	public String getCustomerNaming() {
		return CustomerNaming;
	}

	public void setCustomerNaming(String customerNaming) {
		CustomerNaming = customerNaming;
	}

	/*public float getCapacity() {
		return Capacity;
	}

	public void setCapacity(float capacity) {
		Capacity = capacity;
	}*/

	/*public Integer getUOMID() {
		return UOMID;
	}

	public void setUOMID(Integer uOMID) {
		UOMID = uOMID;
	}*/

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getModuleType() {
		return ModuleType;
	}

	public void setModuleType(String moduleType) {
		ModuleType = moduleType;
	}

	public Integer getActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		ActiveFlag = activeFlag;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(Integer createdBy) {
		CreatedBy = createdBy;
	}

	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public Integer getLastUpdatedBy() {
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}


		
	
}

