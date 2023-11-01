package com.hummersoft.eira.model;


import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;

import jakarta.persistence.Column;
//import javax.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "muser")
public class User implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Integer UserId;

	@Column(name = "usercode")
	private String UserCode;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "encryptpassword")
	private String EncryptPassword;

	public String getEncryptPassword() {
		return EncryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		EncryptPassword = encryptPassword;
	}

	@Column(name = "designation")
	private String Designation;

	@Column(name = "department")
	private String Department;

	@Column(name = "roleid")
	private Integer RoleID;

	@Column(name = "bloodgroup")
	private String BloodGroup;

	@Column(name = "mobilenumber")
	private String MobileNumber;

	@Column(name = "emailid")
	private String EmailID;

	@Column(name = "activeflag")
	private Integer ActiveFlag;

	@Column(name = "creationdate")
	private Date CreationDate;

	public Integer getLastUpdatedBy() {
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "lastupdatedby")
	private Integer LastUpdatedBy;

	@Column(name = "deviceid")
	private String DeviceID;

	/*
	 * 
	 * @Column(name="RequestStatus") private String requestStatus;
	 * 
	 * 
	 * @Column(name="NewDeviceID") private String NewDeviceID;
	 * 
	 * 
	 * @Column(name="DeviceToken") private String DeviceToken;
	 */

	@Column(name = "lastupdateddate")
	private Date LastUpdatedDate;

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public Integer getRoleID() {
		return RoleID;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}

	public String getBloodGroup() {
		return BloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public Integer getActive() {
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

	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstname")
	private String FirstName;

	@Column(name = "lastname")
	private String LastName;

	@Column(name = "shortname")
	private String ShortName;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	@Transient
	private String localIp;

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return DeviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	/**
	 * @return the activeFlag
	 */
	public Integer getActiveFlag() {
		return ActiveFlag;
	}

}
