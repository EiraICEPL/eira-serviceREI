package com.hummersoft.eira.model;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

@Entity
@Table(name = "mEquipment")
public class Equipment implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipmentid")
	private Integer EquipmentId;
	@Transient
	private Integer row;
	//@Transient
	//private List<ErrorDetector> errorDetectors;
	@Transient
	private String warrentyDateInString;
	@Transient
	private String installationDateInString;

	public Equipment() {
		this.DismandalFlag = 0;
	}

	public String getWarrentyDateInString() {
		return warrentyDateInString;
	}

	public void setWarrentyDateInString(String warrentyDateInString) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			WarrantyDate = dateFormat.parse(warrentyDateInString.concat(" ").concat("00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.warrentyDateInString = warrentyDateInString;
	}

	public String getInstallationDateInString() {
		return installationDateInString;
	}

	public void setInstallationDateInString(String installationDateInString) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			InstallationDate = dateFormat.parse(installationDateInString.concat(" ").concat("00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.installationDateInString = installationDateInString;
	}

//	public List<ErrorDetector> getErrorDetectors() {
//		return errorDetectors;
//	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	/*
	 * public void setErrorDetectors(List<ErrorDetector> errorDetectors) {
	 * this.errorDetectors = errorDetectors; }
	 */
	public Integer getSiteID() {
		return SiteID;
	}

	public void setSiteID(Integer siteID) {
		SiteID = siteID;
	}

	@Column(name = "equipmenttypeid")
	private Integer EquipmentTypeID;

	@Column(name = "siteid")
	private Integer SiteID;

	public String getEquipmentTypeName() {
		return EquipmentTypeName;
	}

	public void setEquipmentTypeName(String equipmentTypeName) {
		EquipmentTypeName = equipmentTypeName;
	}

	@Transient
	private String EquipmentTypeName;

	@Transient
	private String SiteName;

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	@Column(name = "equipmentcode")
	private String EquipmentCode;

	@Column(name = "primaryserialnumber")
	private String PrimarySerialNumber;

	@Column(name = "description")
	private String Description;

	@Column(name = "displayname")
	private String DisplayName;

	@Column(name = "dlconfigurationid")
	private Integer DLConfigurationID;

	@Column(name = "remarks")
	private String Remarks;

	@Column(name = "customerreference")
	private String CustomerReference;

	@Column(name = "customernaming")
	private String CustomerNaming;

	@Column(name = "capacity", nullable = false)
	private Double Capacity;
	
	@Column(name = "dccapacity")
	private Double DcCapacity;

	@Column(name = "installationdate")
	private Date InstallationDate;

	@Column(name = "warrantydate")
	private Date WarrantyDate;

	@Column(name = "activeflag")
	private Integer ActiveFlag;

	@Column(name = "creationdate")
	private Date CreationDate;

	@Column(name = "createdby")
	private Integer CreatedBy;

	@Column(name = "lastupdateddate")
	private Date LastUpdatedDate;

	@Column(name = "lastupdatedby")
	private Integer LastUpdatedBy;

	@Column(name = "dismandalflag")
	private Integer DismandalFlag;
	
	@Column(name = "severity")
	private Integer Severity;

	public Integer getDismandalFlag() {
		return DismandalFlag;
	}

	public void setDismandalFlag(Integer dismandalFlag) {
		DismandalFlag = dismandalFlag;
	}

	public Integer getEquipmentId() {
		return EquipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		EquipmentId = equipmentId;
	}

	public Integer getEquipmentTypeID() {
		return EquipmentTypeID;
	}

	public void setEquipmentTypeID(Integer equipmentTypeID) {
		EquipmentTypeID = equipmentTypeID;
	}

	public String getEquipmentCode() {
		return EquipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		EquipmentCode = equipmentCode;
	}

	public String getPrimarySerialNumber() {
		return PrimarySerialNumber;
	}

	public void setPrimarySerialNumber(String primarySerialNumber) {
		PrimarySerialNumber = primarySerialNumber;
	}

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

	public Integer getDLConfigurationID() {
		return DLConfigurationID;
	}

	public void setDLConfigurationID(Integer dLConfigurationID) {
		DLConfigurationID = dLConfigurationID;
	}

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

	public Double getCapacity() {
		return Capacity;
	}

	public void setCapacity(Double capacity) {
		Capacity = capacity;
	}

	public Date getInstallationDate() {
		return InstallationDate;
	}

	public void setInstallationDate(Date installationDate) {
		InstallationDate = installationDate;
	}

	public Date getWarrantyDate() {
		return WarrantyDate;
	}

	public void setWarrantyDate(Date warrantyDate) {
		WarrantyDate = warrantyDate;
	}

	@Transient
	public String WarrantyDateText;
	@Transient
	public String InstallationDateText;

	public String getWarrantyDateText() {
		return WarrantyDateText;
	}

	public void setWarrantyDateText(String warrantyDateText) {
		WarrantyDateText = warrantyDateText;
	}

	public String getInstallationDateText() {
		return InstallationDateText;
	}

	public void setInstallationDateText(String installationDateText) {
		InstallationDateText = installationDateText;
	}

	public Integer getActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		ActiveFlag = activeFlag;
	}
	
	public Integer getSeverity() {
		return Severity;
	}

	public void setSeverity(Integer severity) {
		Severity = severity;
	}
	
	public Double getDcCapacity() {
		return DcCapacity; 
	}

	public void setDcCapacity(Double dccapacity) {
		DcCapacity = dccapacity;
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

	@Transient
	private String SiteReference;

	public String getSiteReference() {
		return SiteReference;
	}

	public void setSiteReference(String siteReference) {
		SiteReference = siteReference;
	}

	@Transient
	private String CategoryName;

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	@Column(name = "equipmentselection")
	private String EquipmentSelection;

	@Column(name = "components")
	private String Components;

	@Column(name = "disconnectrating")
	private String DisconnectRating;

	@Column(name = "disconnecttype")
	private String DisconnectType;
	
	@Column(name = "longitude")
	private String Longitude;
	
	@Column(name = "latitude")
	private String Latitude;
	
	@Column(name = "devicesn")
	private String Devicesn;
	
	@Column(name="secondaryserialnumber")
	private String SecondarySerialNumber;

	public String getSecondaryserialnumber() {
		return SecondarySerialNumber;
	}

	public void setSecondaryserialnumber(String secondaryserialnumber) {
		this.SecondarySerialNumber = secondaryserialnumber;
	}

	public String getEquipmentSelection() {
		return EquipmentSelection;
	}

	public void setEquipmentSelection(String equipmentSelection) {
		EquipmentSelection = equipmentSelection;
	}

	public String getComponents() {
		return Components;
	}

	public void setComponents(String components) {
		Components = components;
	}

	public String getDisconnectRating() {
		return DisconnectRating;
	}

	public void setDisconnectRating(String disconnectRating) {
		DisconnectRating = disconnectRating;
	}

	public String getDisconnectType() {
		return DisconnectType;
	}

	public void setDisconnectType(String disconnectType) {
		DisconnectType = disconnectType;
	}
	
	public String getlongitude() {
		return Longitude;
	}

	public void setlongitude(String longitude) {
		Longitude = longitude;
	}
	
	public String getlatitude() {
		return Latitude;
	}

	public void setlatitude(String latitude) {
		Latitude = latitude;
	}
	
	public String getdevicesn() {
		return Devicesn;
	}

	public void setdevicesn(String devicesn) {
		Devicesn = devicesn;
	}

	

}

