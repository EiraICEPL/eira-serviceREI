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
@Table(name="mequipmentcategory")
public class EquipmentCategory implements Serializable{

	 private static final long serialVersionUID = -723583058586873479L;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name = "categoryid")
	 private Integer CategoryId;
	 
	 @Column(name="equipmentcategory")
	 private String EquipmentCategory;
	 
	 @Column(name="sitetypeid")
	 private Integer SiteTypeID;
	 
	@Transient
	private String SiteTypeName;	
		
		
	 public String getSiteTypeName() {
		return SiteTypeName;
	}

	public void setSiteTypeName(String siteTypeName) {
		SiteTypeName = siteTypeName;
	}

	@Column(name="categorygroup")
	 private String CategoryGroup;
	 
	 @Column(name="categorydescription")
	 private String CategoryDescription;
	 
	 @Column(name="shortname")
	 private String ShortName;
	 
	
 
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
	 
	 @Column(name="remarks")
	 private String Remarks;

	 @Column(name="approvedby")
	 private String ApprovedBy;
	 
	 @Column(name="equipmentcategorycode")
	 private String EquipmentCategoryCode;

	public Integer getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		CategoryId = categoryId;
	}

	public String getEquipmentCategory() {
		return EquipmentCategory;
	}

	public void setEquipmentCategory(String equipmentCategory) {
		EquipmentCategory = equipmentCategory;
	}

	public Integer getSiteTypeID() {
		return SiteTypeID;
	}

	public void setSiteTypeID(Integer siteTypeID) {
		SiteTypeID = siteTypeID;
	}

	public String getCategoryGroup() {
		return CategoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		CategoryGroup = categoryGroup;
	}

	public String getCategoryDescription() {
		return CategoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
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

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getApprovedBy() {
		return ApprovedBy;
	}

	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}

	public String getEquipmentCategoryCode() {
		return EquipmentCategoryCode;
	}

	public void setEquipmentCategoryCode(String equipmentCategoryCode) {
		EquipmentCategoryCode = equipmentCategoryCode;
	}

	
	@Transient
	 private String CreationDateText;
	
	@Transient
	 private String LastUpdatedDateText;


	public String getCreationDateText() {
		return CreationDateText;
	}

	public void setCreationDateText(String creationDateText) {
		CreationDateText = creationDateText;
	}

	public String getLastUpdatedDateText() {
		return LastUpdatedDateText;
	}

	public void setLastUpdatedDateText(String lastUpdatedDateText) {
		LastUpdatedDateText = lastUpdatedDateText;
	}
	
		
	
}
