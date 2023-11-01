package com.hummersoft.eira.model;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "msite")
public class Site implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "siteid")
	private Integer SiteId;

	private Integer serviceflag2, serviceflag3, serviceflag4;

	public Integer getServiceFlag2() {
		return serviceflag2;
	}

	public void setServiceFlag2(Integer serviceFlag2) {
		this.serviceflag2 = serviceFlag2;
	}

	public Integer getServiceFlag3() {
		return serviceflag3;
	}

	public void setServiceFlag3(Integer serviceFlag3) {
		this.serviceflag3 = serviceFlag3;
	}

	public Integer getServiceFlag4() {
		return serviceflag4;
	}

	public void setServiceFlag4(Integer serviceFlag4) {
		this.serviceflag4 = serviceFlag4;
	}

	@Column(name = "customerid")
	private Integer CustomerID;

	@Transient
	private String CustomerName;

	@Column(name = "sitetypeid")
	private Integer SiteTypeID;

	@Transient
	private String SiteTypeName;

	private BigInteger dataloggertypeid, dataloggerid_sensor;

	public BigInteger getDataloggertypeid() {
		return dataloggertypeid;
	}

	public void setDataloggertypeid(BigInteger dataloggertypeid) {
		dataloggertypeid = dataloggertypeid;
	}

	@Column(name = "sitecode")
	private String SiteCode;

	@Column(name = "prodflag")
	private Integer ProdFlag;

	public Integer getProdFlag() {
		return ProdFlag;
	}

	public void setProdFlag(Integer prodFlag) {
		ProdFlag = prodFlag;
	}

	@Column(name = "sitename")
	private String SiteName;

	@Column(name = "sitedescription")
	private String SiteDescription;

	@Column(name = "contactperson")
	private String ContactPerson;

	@Column(name = "address")
	private String Address;

	@Column(name = "city")
	private String City;

	@Column(name = "stateid")
	private Integer StateID;

	@Transient
	private String StateName;

	@Transient
	private String SitePoDateText;

	@Transient
	private String CodText;

	private BigInteger apischeduleinterval;

	private Integer apidatainterval, dataloggerid_energymeter, servicecode_energymeter, dataloggerid_scb,
			servicecode_scb, dataloggerid_tracker, servicecode_tracker;
	private String sensorkey, sensorurl, energymeterkey, energymeterurl, scbkey, scburl, trackerkey, trackerurl;

	public Integer getDataLoggerID_TRACKER() {
		return dataloggerid_tracker;
	}

	public void setDataLoggerID_TRACKER(Integer dataLoggerID_TRACKER) {
		dataloggerid_tracker = dataLoggerID_TRACKER;
	}

	public Integer getServiceCode_TRACKER() {
		return servicecode_tracker;
	}

	public void setServiceCode_TRACKER(Integer serviceCode_TRACKER) {
		servicecode_tracker = serviceCode_TRACKER;
	}

	public Integer getDataLoggerID_SCB() {
		return dataloggerid_scb;
	}

	public void setDataLoggerID_SCB(Integer dataLoggerID_SCB) {
		dataloggerid_scb = dataLoggerID_SCB;
	}

	public Integer getServiceCode_SCB() {
		return servicecode_scb;
	}

	public void setServiceCode_SCB(Integer serviceCode_SCB) {
		servicecode_scb = serviceCode_SCB;
	}

	public Integer getDataLoggerID_ENERGYMETER() {
		return dataloggerid_energymeter;
	}

	public void setDataLoggerID_ENERGYMETER(Integer dataLoggerID_ENERGYMETER) {
		dataloggerid_energymeter = dataLoggerID_ENERGYMETER;
	}

	public Integer getServiceCode_ENERGYMETER() {
		return servicecode_energymeter;
	}

	public void setServiceCode_ENERGYMETER(Integer serviceCode_ENERGYMETER) {
		servicecode_energymeter = serviceCode_ENERGYMETER;
	}

	public Integer getApiDataInterval() {
		return apidatainterval;
	}

	public void setApiDataInterval(Integer apiDataInterval) {
		apidatainterval = apiDataInterval;
	}

	public BigInteger getApiScheduleInterval() {
		return apischeduleinterval;
	}

	public void setApiScheduleInterval(BigInteger apiScheduleInterval) {
		apischeduleinterval = apiScheduleInterval;
	}

	public String getSensorkey() {
		return sensorkey;
	}

	public void setSensorkey(String sensorkey) {
		sensorkey = sensorkey;
	}

	public String getSensorUrl() {
		return sensorurl;
	}

	public void setSensorUrl(String sensorUrl) {
		sensorurl = sensorUrl;
	}

	public String getEnergyMeterkey() {
		return energymeterkey;
	}

	public void setEnergyMeterkey(String energyMeterkey) {
		energymeterkey = energyMeterkey;
	}

	public String getEnergyMeterUrl() {
		return energymeterurl;
	}

	public void setEnergyMeterUrl(String energyMeterUrl) {
		energymeterurl = energyMeterUrl;
	}

	public String getScbkey() {
		return scbkey;
	}

	public void setScbkey(String scbkey) {
		scbkey = scbkey;
	}

	public String getScbUrl() {
		return scburl;
	}

	public void setScbUrl(String scbUrl) {
		scburl = scbUrl;
	}

	public String getTrackerkey() {
		return trackerkey;
	}

	public void setTrackerkey(String trackerkey) {
		trackerkey = trackerkey;
	}

	public String getTrackerUrl() {
		return trackerurl;
	}

	public void setTrackerUrl(String trackerUrl) {
		trackerurl = trackerUrl;
	}

	public String getSitePoDateText() {
		return SitePoDateText;
	}

	public void setSitePoDateText(String sitePoDateText) {
		SitePoDateText = sitePoDateText;
	}

	@Column(name = "countryid")
	private Integer CountryID;

	@Transient
	private String CountryName;

	@Column(name = "postalcode")
	private String PostalCode;

	@Column(name = "longitude")
	private String Longitude;

	@Column(name = "latitude")
	private String Latitude;

	@Column(name = "altitude")
	private String Altitude;

	@Column(name = "siteimage")
	private String SiteImage;

	@Column(name = "installationcapacity")
	private double InstallationCapacity;

	@Column(name = "siteponumber")
	private String SitePONumber;

	@Column(name = "sitepodate")
	private Date SitePODate;

	@Column(name = "cod")
	private Date Cod;

	/*
	 * @Column(name="SiteHandoverDate") private Date SiteHandoverDate;
	 * 
	 * 
	 * @Column(name="SiteCommisioningDate") private Date SiteCommisioningDate;
	 * 
	 * 
	 * @Column(name="InstalledOn") private Date InstalledOn;
	 */

	@Column(name = "siteoperator")
	private String SiteOperator;

	@Column(name = "sitemanafacturer")
	private String SiteManafacturer;

	@Column(name = "modulename")
	private String ModuleName;

	@Column(name = "communicationtype")
	private String CommunicationType;

	@Column(name = "collectiontype")
	private String CollectionType;

	@Column(name = "filetype")
	private String FileType;

	@Column(name = "customerreference")
	private String CustomerReference;

	@Column(name = "customernaming")
	private String CustomerNaming;

	/*
	 * @Column(name="Income") private float Income;
	 */

	@Column(name = "currencyid")
	private Integer CurrencyID;

	@Transient
	private String CurrencyName;

	@Column(name = "emailid")
	private String EmailID;

	@Column(name = "mobile")
	private String Mobile;

	@Column(name = "telephone")
	private String Telephone;

	@Column(name = "fax")
	private String Fax;

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

	@Column(name = "dataloggerid")
	private Integer DataLoggerID;

	@Column(name = "operationmode")
	private Integer OperationMode;

	@Column(name = "remoteftpserver")
	private String RemoteFtpServer;

	@Column(name = "remoteftpusername")
	private String RemoteFtpUserName;

	@Column(name = "remoteftppassword")
	private String RemoteFtpPassword;

	@Column(name = "remoteftpserverport")
	private String RemoteFtpServerPort;

	@Column(name = "remoteftpdirectorypath")
	private String RemoteFtpDirectoryPath;

	@Column(name = "fileprefix")
	private String FilePrefix;

	@Column(name = "filesuffix")
	private String FileSuffix;

	@Column(name = "filesubstring")
	private String FileSubstring;

	@Column(name = "subfolder")
	private Integer SubFolder;

	@Column(name = "localftpdirectory")
	private String LocalFtpDirectory;

	@Column(name = "localftpdirectorypath")
	private String LocalFtpDirectoryPath;

	@Column(name = "localftphomedirectory")
	private String LocalFtpHomeDirectory;

	@Column(name = "localftpusername")
	private String LocalFtpUserName;

	@Column(name = "localftppassword")
	private String LocalFtpPassword;

	@Column(name = "apiurl")
	private String ApiUrl;

	@Column(name = "apikey")
	private String ApiKey;

	/*
	 * @Column(name="ServiceCode") private Integer ServiceCode;
	 */

	@Column(name = "localftpdirectorypath_sensor")
	private String LocalFtpDirectoryPath_Sensor;

	@Column(name = "localftpdirectorypath_alarm")
	private String LocalFtpDirectoryPath_Alarm;

	@Column(name = "localftpdirectorypath_log")
	private String LocalFtpDirectoryPath_Log;

	@Column(name = "localftpdirectory_sensor")
	private String LocalFtpDirectory_Sensor;

	@Column(name = "localftpdirectory_alarm")
	private String LocalFtpDirectory_Alarm;

	@Column(name = "localftpdirectory_log")
	private String LocalFtpDirectory_Log;

	@Column(name = "dataloggerid_alarm")
	private Integer DataLoggerID_Alarm;

	@Column(name = "dataloggerid_log")
	private Integer DataLoggerID_Log;

	@Column(name = "servicecode_sensor")
	private Integer ServiceCode_Sensor;

	@Column(name = "servicecode_alarm")
	private Integer ServiceCode_Alarm;

	@Column(name = "servicecode_log")
	private Integer ServiceCode_Log;

	@Column(name = "timezone")
	private String Timezone;

	private BigInteger timezoneoffsetmin;

	public BigInteger getTimeZoneOffSetMin() {
		return timezoneoffsetmin;
	}

	public void setTimeZoneOffSetMin(BigInteger timeZoneOffSetMin) {
		this.timezoneoffsetmin = timeZoneOffSetMin;
	}

	@Column(name = "localftpdirectory_modbus")
	private String LocalFtpDirectory_Modbus;

	@Column(name = "localftpdirectory_inverter")
	private String LocalFtpDirectory_Inverter;

	@Column(name = "localftpdirectory_data")
	private String LocalFtpDirectory_Data;

	@Column(name = "localftpdirectorypath_modbus")
	private String LocalFtpDirectoryPath_Modbus;

	@Column(name = "localftpdirectorypath_inverter")
	private String LocalFtpDirectoryPath_Inverter;

	@Column(name = "localftpdirectorypath_data")
	private String LocalFtpDirectoryPath_Data;

	@Column(name = "dataloggerid_modbus")
	private Integer DataLoggerID_Modbus;

	@Column(name = "dataloggerid_inverter")
	private Integer DataLoggerID_Inverter;

	@Column(name = "dataloggerid_data")
	private Integer DataLoggerID_Data;

	@Column(name = "servicecode_modbus")
	private Integer ServiceCode_Modbus;

	@Column(name = "servicecode_inverter")
	private Integer ServiceCode_Inverter;

	@Column(name = "servicecode_data")
	private Integer ServiceCode_Data;

	public Integer getTodayEnergyFlag() {
		return TodayEnergyFlag;
	}

	public void setTodayEnergyFlag(Integer todayEnergyFlag) {
		TodayEnergyFlag = todayEnergyFlag;
	}

	@Column(name = "todayenergyflag")
	private Integer TodayEnergyFlag;

	/*
	 * @Transient private SiteStatus siteStatus;
	 * 
	 * 
	 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "mSite", cascade =
	 * CascadeType.ALL) public SiteStatus getSiteStatus() { return this.siteStatus;
	 * }
	 * 
	 * public void setSiteStatus(SiteStatus siteStatus) { this.siteStatus =
	 * siteStatus; }
	 */

	/*
	 * @Transient private Set<SiteStatus> siteStatus = new HashSet<SiteStatus>(0);
	 * 
	 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "mSite") public Set<SiteStatus>
	 * getSiteStatus() { return siteStatus; }
	 * 
	 * public void setSiteStatus(Set<SiteStatus> siteStatus) { siteStatus =
	 * siteStatus; }
	 */

	public Integer getSiteId() {
		return SiteId;
	}

	public void setSiteId(Integer siteId) {
		SiteId = siteId;
	}

	public Integer getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}

	public Integer getSiteTypeID() {
		return SiteTypeID;
	}

	public void setSiteTypeID(Integer siteTypeID) {
		SiteTypeID = siteTypeID;
	}

	public String getSiteCode() {
		return SiteCode;
	}

	public void setSiteCode(String siteCode) {
		SiteCode = siteCode;
	}

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	public String getSiteDescription() {
		return SiteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		SiteDescription = siteDescription;
	}

	public String getContactPerson() {
		return ContactPerson;
	}

	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public Integer getStateID() {
		return StateID;
	}

	public void setStateID(Integer stateID) {
		StateID = stateID;
	}

	public Integer getCountryID() {
		return CountryID;
	}

	public void setCountryID(Integer countryID) {
		CountryID = countryID;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getAltitude() {
		return Altitude;
	}

	public void setAltitude(String altitude) {
		Altitude = altitude;
	}

	public String getSiteImage() {
		return SiteImage;
	}

	public void setSiteImage(String siteImage) {
		SiteImage = siteImage;
	}

	public double getInstallationCapacity() {
		return InstallationCapacity;
	}

	public void setInstallationCapacity(double installationCapacity) {
		InstallationCapacity = installationCapacity;
	}

	public String getSitePONumber() {
		return SitePONumber;
	}

	public void setSitePONumber(String sitePONumber) {
		SitePONumber = sitePONumber;
	}

	public Date getSitePODate() {
		return SitePODate;
	}

	public void setSitePODate(Date sitePODate) {
		SitePODate = sitePODate;
	}

	/*
	 * public Date getSiteHandoverDate() { return SiteHandoverDate; }
	 * 
	 * public void setSiteHandoverDate(Date siteHandoverDate) { SiteHandoverDate =
	 * siteHandoverDate; }
	 * 
	 * public Date getSiteCommisioningDate() { return SiteCommisioningDate; }
	 * 
	 * public void setSiteCommisioningDate(Date siteCommisioningDate) {
	 * SiteCommisioningDate = siteCommisioningDate; }
	 * 
	 * public Date getInstalledOn() { return InstalledOn; }
	 * 
	 * public void setInstalledOn(Date installedOn) { InstalledOn = installedOn; }
	 */
	public String getSiteOperator() {
		return SiteOperator;
	}

	public void setSiteOperator(String siteOperator) {
		SiteOperator = siteOperator;
	}

	public String getSiteManafacturer() {
		return SiteManafacturer;
	}

	public void setSiteManafacturer(String siteManafacturer) {
		SiteManafacturer = siteManafacturer;
	}

	public String getModuleName() {
		return ModuleName;
	}

	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}

	public String getCommunicationType() {
		return CommunicationType;
	}

	public void setCommunicationType(String communicationType) {
		CommunicationType = communicationType;
	}

	public String getCollectionType() {
		return CollectionType;
	}

	public void setCollectionType(String collectionType) {
		CollectionType = collectionType;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String fileType) {
		FileType = fileType;
	}

	/*
	 * public float getIncome() { return Income; }
	 * 
	 * public void setIncome(float income) { Income = income; }
	 */
	public Integer getCurrencyID() {
		return CurrencyID;
	}

	public void setCurrencyID(Integer currencyID) {
		CurrencyID = currencyID;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
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

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getSiteTypeName() {
		return SiteTypeName;
	}

	public void setSiteTypeName(String siteTypeName) {
		SiteTypeName = siteTypeName;
	}

	public String getStateName() {
		return StateName;
	}

	public void setStateName(String stateName) {
		StateName = stateName;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getCurrencyName() {
		return CurrencyName;
	}

	public void setCurrencyName(String currencyName) {
		CurrencyName = currencyName;
	}

	public Integer getDataLoggerID() {
		return DataLoggerID;
	}

	public void setDataLoggerID(Integer dataLoggerID) {
		DataLoggerID = dataLoggerID;
	}

	public Integer getOperationMode() {
		return OperationMode;
	}

	public void setOperationMode(Integer operationMode) {
		OperationMode = operationMode;
	}

	public String getFilePrefix() {
		return FilePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		FilePrefix = filePrefix;
	}

	public String getFileSuffix() {
		return FileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		FileSuffix = fileSuffix;
	}

	public String getFileSubstring() {
		return FileSubstring;
	}

	public void setFileSubstring(String fileSubstring) {
		FileSubstring = fileSubstring;
	}

	public Integer getSubFolder() {
		return SubFolder;
	}

	public void setSubFolder(Integer subFolder) {
		SubFolder = subFolder;
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

	public String getRemoteFtpServer() {
		return RemoteFtpServer;
	}

	public void setRemoteFtpServer(String remoteFtpServer) {
		RemoteFtpServer = remoteFtpServer;
	}

	public String getRemoteFtpUserName() {
		return RemoteFtpUserName;
	}

	public void setRemoteFtpUserName(String remoteFtpUserName) {
		RemoteFtpUserName = remoteFtpUserName;
	}

	public String getRemoteFtpPassword() {
		return RemoteFtpPassword;
	}

	public void setRemoteFtpPassword(String remoteFtpPassword) {
		RemoteFtpPassword = remoteFtpPassword;
	}

	public String getRemoteFtpServerPort() {
		return RemoteFtpServerPort;
	}

	public void setRemoteFtpServerPort(String remoteFtpServerPort) {
		RemoteFtpServerPort = remoteFtpServerPort;
	}

	public String getRemoteFtpDirectoryPath() {
		return RemoteFtpDirectoryPath;
	}

	public void setRemoteFtpDirectoryPath(String remoteFtpDirectoryPath) {
		RemoteFtpDirectoryPath = remoteFtpDirectoryPath;
	}

	public String getLocalFtpDirectory() {
		return LocalFtpDirectory;
	}

	public void setLocalFtpDirectory(String localFtpDirectory) {
		LocalFtpDirectory = localFtpDirectory;
	}

	public String getLocalFtpDirectoryPath() {
		return LocalFtpDirectoryPath;
	}

	public void setLocalFtpDirectoryPath(String localFtpDirectoryPath) {
		LocalFtpDirectoryPath = localFtpDirectoryPath;
	}

	public String getLocalFtpHomeDirectory() {
		return LocalFtpHomeDirectory;
	}

	public void setLocalFtpHomeDirectory(String localFtpHomeDirectory) {
		LocalFtpHomeDirectory = localFtpHomeDirectory;
	}

	public String getLocalFtpUserName() {
		return LocalFtpUserName;
	}

	public void setLocalFtpUserName(String localFtpUserName) {
		LocalFtpUserName = localFtpUserName;
	}

	public String getLocalFtpPassword() {
		return LocalFtpPassword;
	}

	public void setLocalFtpPassword(String localFtpPassword) {
		LocalFtpPassword = localFtpPassword;
	}

	public String getApiUrl() {
		return ApiUrl;
	}

	public void setApiUrl(String apiUrl) {
		ApiUrl = apiUrl;
	}

	public String getApiKey() {
		return ApiKey;
	}

	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}

	/*
	 * public String getServiceCode() { return ServiceCode; }
	 * 
	 * public void setServiceCode(String serviceCode) { ServiceCode = serviceCode; }
	 */
	public String getLocalFtpDirectoryPath_Sensor() {
		return LocalFtpDirectoryPath_Sensor;
	}

	public void setLocalFtpDirectoryPath_Sensor(String localFtpDirectoryPath_Sensor) {
		LocalFtpDirectoryPath_Sensor = localFtpDirectoryPath_Sensor;
	}

	public String getLocalFtpDirectoryPath_Alarm() {
		return LocalFtpDirectoryPath_Alarm;
	}

	public void setLocalFtpDirectoryPath_Alarm(String localFtpDirectoryPath_Alarm) {
		LocalFtpDirectoryPath_Alarm = localFtpDirectoryPath_Alarm;
	}

	public String getLocalFtpDirectoryPath_Log() {
		return LocalFtpDirectoryPath_Log;
	}

	public void setLocalFtpDirectoryPath_Log(String localFtpDirectoryPath_Log) {
		LocalFtpDirectoryPath_Log = localFtpDirectoryPath_Log;
	}

	public String getLocalFtpDirectory_Sensor() {
		return LocalFtpDirectory_Sensor;
	}

	public void setLocalFtpDirectory_Sensor(String localFtpDirectory_Sensor) {
		LocalFtpDirectory_Sensor = localFtpDirectory_Sensor;
	}

	public String getLocalFtpDirectory_Alarm() {
		return LocalFtpDirectory_Alarm;
	}

	public void setLocalFtpDirectory_Alarm(String localFtpDirectory_Alarm) {
		LocalFtpDirectory_Alarm = localFtpDirectory_Alarm;
	}

	public String getLocalFtpDirectory_Log() {
		return LocalFtpDirectory_Log;
	}

	public void setLocalFtpDirectory_Log(String localFtpDirectory_Log) {
		LocalFtpDirectory_Log = localFtpDirectory_Log;
	}

	public BigInteger getDataLoggerID_Sensor() {
		return dataloggerid_sensor;
	}

	public void setDataLoggerID_Sensor(BigInteger dataLoggerID_Sensor) {
		dataloggerid_sensor = dataLoggerID_Sensor;
	}

	public Integer getDataLoggerID_Alarm() {
		return DataLoggerID_Alarm;
	}

	public void setDataLoggerID_Alarm(Integer dataLoggerID_Alarm) {
		DataLoggerID_Alarm = dataLoggerID_Alarm;
	}

	public Integer getDataLoggerID_Log() {
		return DataLoggerID_Log;
	}

	public void setDataLoggerID_Log(Integer dataLoggerID_Log) {
		DataLoggerID_Log = dataLoggerID_Log;
	}

	public Integer getServiceCode_Sensor() {
		return ServiceCode_Sensor;
	}

	public void setServiceCode_Sensor(Integer serviceCode_Sensor) {
		ServiceCode_Sensor = serviceCode_Sensor;
	}

	public Integer getServiceCode_Alarm() {
		return ServiceCode_Alarm;
	}

	public void setServiceCode_Alarm(Integer serviceCode_Alarm) {
		ServiceCode_Alarm = serviceCode_Alarm;
	}

	public Integer getServiceCode_Log() {
		return ServiceCode_Log;
	}

	public void setServiceCode_Log(Integer serviceCode_Log) {
		ServiceCode_Log = serviceCode_Log;
	}

	public String getTimezone() {
		return Timezone;
	}

	public void setTimezone(String timezone) {
		Timezone = timezone;
	}

	public String getLocalFtpDirectory_Modbus() {
		return LocalFtpDirectory_Modbus;
	}

	public void setLocalFtpDirectory_Modbus(String localFtpDirectory_Modbus) {
		LocalFtpDirectory_Modbus = localFtpDirectory_Modbus;
	}

	public String getLocalFtpDirectory_Inverter() {
		return LocalFtpDirectory_Inverter;
	}

	public void setLocalFtpDirectory_Inverter(String localFtpDirectory_Inverter) {
		LocalFtpDirectory_Inverter = localFtpDirectory_Inverter;
	}

	public String getLocalFtpDirectory_Data() {
		return LocalFtpDirectory_Data;
	}

	public void setLocalFtpDirectory_Data(String localFtpDirectory_Data) {
		LocalFtpDirectory_Data = localFtpDirectory_Data;
	}

	public String getLocalFtpDirectoryPath_Modbus() {
		return LocalFtpDirectoryPath_Modbus;
	}

	public void setLocalFtpDirectoryPath_Modbus(String localFtpDirectoryPath_Modbus) {
		LocalFtpDirectoryPath_Modbus = localFtpDirectoryPath_Modbus;
	}

	public String getLocalFtpDirectoryPath_Inverter() {
		return LocalFtpDirectoryPath_Inverter;
	}

	public void setLocalFtpDirectoryPath_Inverter(String localFtpDirectoryPath_Inverter) {
		LocalFtpDirectoryPath_Inverter = localFtpDirectoryPath_Inverter;
	}

	public String getLocalFtpDirectoryPath_Data() {
		return LocalFtpDirectoryPath_Data;
	}

	public void setLocalFtpDirectoryPath_Data(String localFtpDirectoryPath_Data) {
		LocalFtpDirectoryPath_Data = localFtpDirectoryPath_Data;
	}

	public Integer getDataLoggerID_Modbus() {
		return DataLoggerID_Modbus;
	}

	public void setDataLoggerID_Modbus(Integer dataLoggerID_Modbus) {
		DataLoggerID_Modbus = dataLoggerID_Modbus;
	}

	public Integer getDataLoggerID_Inverter() {
		return DataLoggerID_Inverter;
	}

	public void setDataLoggerID_Inverter(Integer dataLoggerID_Inverter) {
		DataLoggerID_Inverter = dataLoggerID_Inverter;
	}

	public Integer getDataLoggerID_Data() {
		return DataLoggerID_Data;
	}

	public void setDataLoggerID_Data(Integer dataLoggerID_Data) {
		DataLoggerID_Data = dataLoggerID_Data;
	}

	public Integer getServiceCode_Modbus() {
		return ServiceCode_Modbus;
	}

	public void setServiceCode_Modbus(Integer serviceCode_Modbus) {
		ServiceCode_Modbus = serviceCode_Modbus;
	}

	public Integer getServiceCode_Inverter() {
		return ServiceCode_Inverter;
	}

	public void setServiceCode_Inverter(Integer serviceCode_Inverter) {
		ServiceCode_Inverter = serviceCode_Inverter;
	}

	public Integer getServiceCode_Data() {
		return ServiceCode_Data;
	}

	public void setServiceCode_Data(Integer serviceCode_Data) {
		ServiceCode_Data = serviceCode_Data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "contractid")
	private String ContractId;

	@Column(name = "typeofcontract")
	private String TypeOfContract;

	@Column(name = "servicepackage")
	private String ServicePackage;

	@Column(name = "daf_service")
	private String Daf_Service;

	@Column(name = "daf_frequency")
	private String Daf_Frequency;

	@Column(name = "tbt_service")
	private String Tbt_Service;

	@Column(name = "tbt_frequency")
	private String Tbt_Frequency;

	@Column(name = "tat_service")
	private String Tat_Service;
	@Column(name = "tat_frequency")
	private String Tat_Frequency;
	@Column(name = "sym_service")
	private String Sym_Service;
	@Column(name = "sym_frequency")
	private String Sym_frequency;
	@Column(name = "tls_service")
	private String Tls_Service;
	@Column(name = "tls_frequency")
	private String Tls_Frequency;

	@Column(name = "tlm_service")
	private String Tlm_Service;
	@Column(name = "tlm_frequency")
	private String Tlm_Frequency;
	@Column(name = "com_service")
	private String Com_Service;
	@Column(name = "com_frequency")
	private String Com_Frequency;
	@Column(name = "rt_service")
	private String Rt_Service;
	@Column(name = "rt_frequency")
	private String Rt_Frequency;

	@Column(name = "security_service")
	private String Security_Service;
	@Column(name = "security_frequency")
	private String Security_Frequency;
	@Column(name = "rowfc_service")
	private String Rowfc_Service;
	@Column(name = "rowfc_frequency")
	private String Rowfc_Frequency;
	@Column(name = "dti_service")
	private String Dti_Service;
	@Column(name = "dti_frequency")
	private String Dti_Frequency;

	@Column(name = "di_service")
	private String Di_Service;

	@Column(name = "di_frequency")
	private String Di_Frequency;

	@Column(name = "sact_service")
	private String Sact_Service;

	@Column(name = "sact_frequency")
	private String Sact_Frequency;

	@Column(name = "eltml_service")
	private String Eltml_Service;

	@Column(name = "eltml_frequency")
	private String Eltml_Frequency;

	@Column(name = "eltms_service")
	private String Eltms_Service;
	@Column(name = "eltms_frequency")
	private String Eltms_Frequency;
	@Column(name = "ir_service")
	private String Ir_Service;
	@Column(name = "ir_frequency")
	private String Ir_Frequency;
	@Column(name = "revampdc_service")
	private String Revampdc_Service;
	@Column(name = "revampdc_frequency")
	private String Revampdc_Frequency;

	@Column(name = "revampaclt_service")
	private String Revampaclt_Service;
	@Column(name = "revampaclt_frequency")
	private String Revampaclt_Frequency;
	@Column(name = "revampacht_service")
	private String Revampacht_Service;
	@Column(name = "revampacht_frequency")
	private String Revampacht_Frequency;
	@Column(name = "revampacht_service_civil")
	private String Revampacht_Service_Civil;
	@Column(name = "revampacht_frequency_civil")
	private String Revampacht_Frequency_Civil;

	@Column(name = "jmr_service")
	private String Jmr_Service;

	@Column(name = "jmr_frequency")
	private String Jmr_Frequency;

	@Column(name = "sldc_documentation")
	private String Sldc_Documentation;

	@Column(name = "sldc_frequency")
	private String Sldc_Frequency;

	@Column(name = "spares_management")
	private String Spares_Management;

	@Column(name = "sm_frequency")
	private String Sm_frequency;

	@Column(name = "cleaningcycle")
	private String CleaningCycle;

	@Column(name = "cleaningcycle_frequency")
	private String CleaningCycle_Frequency;

	@Column(name = "vegetation_mangement")
	private String VEGETATION_MANGEMENT;

	@Column(name = "vm_frequency")
	private String Vm_Frequency;

	@Column(name = "plantdown_issueidentified")
	private String Plantdown_Issueidentified;

	@Column(name = "plantdown_assigned")
	private String Plantdown_Assigned;

	@Column(name = "plantdown_resolved")
	private String Plantdown_Resolved;

	public String getPlantdown_Resolved() {
		return Plantdown_Resolved;
	}

	public void setPlantdown_Resolved(String plantdown_Resolved) {
		Plantdown_Resolved = plantdown_Resolved;
	}

	@Column(name = "plantwarning_issueidentified")
	private String Plantwarning_Issueidentified;

	@Column(name = "plantwarning_assigned")
	private String Plantwarning_Assigned;

	@Column(name = "plantwarning_resolved")
	private String Plantwarning_Resolved;

	@Column(name = "equgendown_issueidentified")
	private String Equgendown_Issueidentified;

	@Column(name = "equgendown_assigned")
	private String Equgendown_Assigned;

	@Column(name = "equgendown_resolved")
	private String Equgendown_Resolved;

	@Column(name = "equgenwarning_issueidentified")
	private String Equgenwarning_Issueidentified;

	@Column(name = "equgenwarning_assigned")
	private String Equgenwarning_Assigned;

	@Column(name = "equgenwarning_resolved")
	private String Equgenwarning_Resolved;

	@Column(name = "equgenoffline_issueidentified")
	private String Equgenoffline_Issueidentified;

	@Column(name = "equgenoffline_assigned")
	private String Equgenoffline_Assigned;

	@Column(name = "equgenoffline_resolved")
	private String Equgenoffline_Resolved;

	@Column(name = "equnongendown_issueidentified")
	private String Equnongendown_Issueidentified;

	@Column(name = "equnongendown_assigned")
	private String Equnongendown_Assigned;

	@Column(name = "equnongendown_resolved")
	private String Equnongendown_Resolved;

	@Column(name = "equnongenwarning_issueidentified")
	private String Equnongenwarning_Issueidentified;

	@Column(name = "equnongenwarning_assigned")
	private String Equnongenwarning_Assigned;

	@Column(name = "equnongenwarning_resolved")
	private String Equnongenwarning_Resolved;

	@Column(name = "equnongenoffline_issueidentified")
	private String Equnongenoffline_Issueidentified;

	@Column(name = "equnongenoffline_assigned")
	private String Equnongenoffline_Assigned;

	@Column(name = "equnongenoffline_resolved")
	private String Equnongenoffline_Resolved;

	@Column(name = "commencementofwork")
	private Date Commencementofwork;
	@Transient
	private String CommercementofworkText;

	@Column(name = "performanceguaranteedamages")
	private String Performanceguaranteedamages;

	@Column(name = "sld")
	private String Sld;

	@Column(name = "pvsyst")
	private String Pvsyst;

	@Column(name = "constructionlayout")
	private String ConstructionLayout;

	@Column(name = "warrantycertificate")
	private String WarrantyCertificate;

	@Column(name = "inssurancecertificate")
	private String InssuranceCertificate;

	@Column(name = "approvalcertificate")
	private String ApprovalCertificate;

	@Column(name = "permits")
	private String Permits;

	@Column(name = "testcertificate")
	private String TestCertificate;

	@Column(name = "servicereport")
	private String ServiceReport;

	@Column(name = "incidentreport")
	private String IncidentReport;

	@Column(name = "preventivemaintenance")
	private String PreventiveMaintenance;

	@Column(name = "calibrationreport")
	private String CalibrationReport;

	public String getCommercementofworkText() {
		return CommercementofworkText;
	}

	public void setCommercementofworkText(String commercementofworkText) {
		CommercementofworkText = commercementofworkText;
	}

	public String getContractId() {
		return ContractId;
	}

	public void setContractId(String contractId) {
		ContractId = contractId;
	}

	public String getTypeOfContract() {
		return TypeOfContract;
	}

	public void setTypeOfContract(String typeOfContract) {
		TypeOfContract = typeOfContract;
	}

	public String getServicePackage() {
		return ServicePackage;
	}

	public void setServicePackage(String servicePackage) {
		ServicePackage = servicePackage;
	}

	public String getDaf_Service() {
		return Daf_Service;
	}

	public void setDaf_Service(String daf_Service) {
		Daf_Service = daf_Service;
	}

	public String getDaf_Frequency() {
		return Daf_Frequency;
	}

	public void setDaf_Frequency(String daf_Frequency) {
		Daf_Frequency = daf_Frequency;
	}

	public String getTbt_Service() {
		return Tbt_Service;
	}

	public void setTbt_Service(String tbt_Service) {
		Tbt_Service = tbt_Service;
	}

	public String getTbt_Frequency() {
		return Tbt_Frequency;
	}

	public void setTbt_Frequency(String tbt_Frequency) {
		Tbt_Frequency = tbt_Frequency;
	}

	public String getTat_Service() {
		return Tat_Service;
	}

	public void setTat_Service(String tat_Service) {
		Tat_Service = tat_Service;
	}

	public String getTat_Frequency() {
		return Tat_Frequency;
	}

	public void setTat_Frequency(String tat_Frequency) {
		Tat_Frequency = tat_Frequency;
	}

	public String getSym_Service() {
		return Sym_Service;
	}

	public void setSym_Service(String sym_Service) {
		Sym_Service = sym_Service;
	}

	public String getSym_frequency() {
		return Sym_frequency;
	}

	public void setSym_frequency(String sym_frequency) {
		Sym_frequency = sym_frequency;
	}

	public String getTls_Service() {
		return Tls_Service;
	}

	public void setTls_Service(String tls_Service) {
		Tls_Service = tls_Service;
	}

	public String getTls_Frequency() {
		return Tls_Frequency;
	}

	public void setTls_Frequency(String tls_Frequency) {
		Tls_Frequency = tls_Frequency;
	}

	public String getTlm_Service() {
		return Tlm_Service;
	}

	public void setTlm_Service(String tlm_Service) {
		Tlm_Service = tlm_Service;
	}

	public String getTlm_Frequency() {
		return Tlm_Frequency;
	}

	public void setTlm_Frequency(String tlm_Frequency) {
		Tlm_Frequency = tlm_Frequency;
	}

	public String getCom_Service() {
		return Com_Service;
	}

	public void setCom_Service(String com_Service) {
		Com_Service = com_Service;
	}

	public String getCom_Frequency() {
		return Com_Frequency;
	}

	public void setCom_Frequency(String com_Frequency) {
		Com_Frequency = com_Frequency;
	}

	public String getRt_Service() {
		return Rt_Service;
	}

	public void setRt_Service(String rt_Service) {
		Rt_Service = rt_Service;
	}

	public String getRt_Frequency() {
		return Rt_Frequency;
	}

	public void setRt_Frequency(String rt_Frequency) {
		Rt_Frequency = rt_Frequency;
	}

	public String getRowfc_Service() {
		return Rowfc_Service;
	}

	public void setRowfc_Service(String rowfc_Service) {
		Rowfc_Service = rowfc_Service;
	}

	public String getRowfc_Frequency() {
		return Rowfc_Frequency;
	}

	public void setRowfc_Frequency(String rowfc_Frequency) {
		Rowfc_Frequency = rowfc_Frequency;
	}

	public String getDti_Service() {
		return Dti_Service;
	}

	public void setDti_Service(String dti_Service) {
		Dti_Service = dti_Service;
	}

	public String getDti_Frequency() {
		return Dti_Frequency;
	}

	public void setDti_Frequency(String dti_Frequency) {
		Dti_Frequency = dti_Frequency;
	}

	public String getDi_Service() {
		return Di_Service;
	}

	public void setDi_Service(String di_Service) {
		Di_Service = di_Service;
	}

	public String getDi_Frequency() {
		return Di_Frequency;
	}

	public void setDi_Frequency(String di_Frequency) {
		Di_Frequency = di_Frequency;
	}

	public String getSact_Service() {
		return Sact_Service;
	}

	public void setSact_Service(String sact_Service) {
		Sact_Service = sact_Service;
	}

	public String getSact_Frequency() {
		return Sact_Frequency;
	}

	public void setSact_Frequency(String sact_Frequency) {
		Sact_Frequency = sact_Frequency;
	}

	public String getEltml_Service() {
		return Eltml_Service;
	}

	public void setEltml_Service(String eltml_Service) {
		Eltml_Service = eltml_Service;
	}

	public String getEltml_Frequency() {
		return Eltml_Frequency;
	}

	public void setEltml_Frequency(String eltml_Frequency) {
		Eltml_Frequency = eltml_Frequency;
	}

	public String getEltms_Service() {
		return Eltms_Service;
	}

	public void setEltms_Service(String eltms_Service) {
		Eltms_Service = eltms_Service;
	}

	public String getEltms_Frequency() {
		return Eltms_Frequency;
	}

	public void setEltms_Frequency(String eltms_Frequency) {
		Eltms_Frequency = eltms_Frequency;
	}

	public String getIr_Service() {
		return Ir_Service;
	}

	public void setIr_Service(String ir_Service) {
		Ir_Service = ir_Service;
	}

	public String getIr_Frequency() {
		return Ir_Frequency;
	}

	public void setIr_Frequency(String ir_Frequency) {
		Ir_Frequency = ir_Frequency;
	}

	public String getRevampdc_Service() {
		return Revampdc_Service;
	}

	public void setRevampdc_Service(String revampdc_Service) {
		Revampdc_Service = revampdc_Service;
	}

	public String getRevampdc_Frequency() {
		return Revampdc_Frequency;
	}

	public void setRevampdc_Frequency(String revampdc_Frequency) {
		Revampdc_Frequency = revampdc_Frequency;
	}

	public String getRevampaclt_Service() {
		return Revampaclt_Service;
	}

	public void setRevampaclt_Service(String revampaclt_Service) {
		Revampaclt_Service = revampaclt_Service;
	}

	public String getRevampaclt_Frequency() {
		return Revampaclt_Frequency;
	}

	public void setRevampaclt_Frequency(String revampaclt_Frequency) {
		Revampaclt_Frequency = revampaclt_Frequency;
	}

	public String getRevampacht_Service() {
		return Revampacht_Service;
	}

	public void setRevampacht_Service(String revampacht_Service) {
		Revampacht_Service = revampacht_Service;
	}

	public String getRevampacht_Frequency() {
		return Revampacht_Frequency;
	}

	public void setRevampacht_Frequency(String revampacht_Frequency) {
		Revampacht_Frequency = revampacht_Frequency;
	}

	public String getRevampacht_Service_Civil() {
		return Revampacht_Service_Civil;
	}

	public void setRevampacht_Service_Civil(String revampacht_Service_Civil) {
		Revampacht_Service_Civil = revampacht_Service_Civil;
	}

	public String getRevampacht_Frequency_Civil() {
		return Revampacht_Frequency_Civil;
	}

	public void setRevampacht_Frequency_Civil(String revampacht_Frequency_Civil) {
		Revampacht_Frequency_Civil = revampacht_Frequency_Civil;
	}

	public String getJmr_Service() {
		return Jmr_Service;
	}

	public void setJmr_Service(String jmr_Service) {
		Jmr_Service = jmr_Service;
	}

	public String getJmr_Frequency() {
		return Jmr_Frequency;
	}

	public void setJmr_Frequency(String jmr_Frequency) {
		Jmr_Frequency = jmr_Frequency;
	}

	public String getSldc_Documentation() {
		return Sldc_Documentation;
	}

	public void setSldc_Documentation(String sldc_Documentation) {
		Sldc_Documentation = sldc_Documentation;
	}

	public String getSldc_Frequency() {
		return Sldc_Frequency;
	}

	public void setSldc_Frequency(String sldc_Frequency) {
		Sldc_Frequency = sldc_Frequency;
	}

	public String getSpares_Management() {
		return Spares_Management;
	}

	public void setSpares_Management(String spares_Management) {
		Spares_Management = spares_Management;
	}

	public String getSm_frequency() {
		return Sm_frequency;
	}

	public void setSm_frequency(String sm_frequency) {
		Sm_frequency = sm_frequency;
	}

	public String getCleaningCycle() {
		return CleaningCycle;
	}

	public void setCleaningCycle(String cleaningCycle) {
		CleaningCycle = cleaningCycle;
	}

	public String getCleaningCycle_Frequency() {
		return CleaningCycle_Frequency;
	}

	public void setCleaningCycle_Frequency(String cleaningCycle_Frequency) {
		CleaningCycle_Frequency = cleaningCycle_Frequency;
	}

	public String getVEGETATION_MANGEMENT() {
		return VEGETATION_MANGEMENT;
	}

	public void setVEGETATION_MANGEMENT(String vEGETATION_MANGEMENT) {
		VEGETATION_MANGEMENT = vEGETATION_MANGEMENT;
	}

	public String getVm_Frequency() {
		return Vm_Frequency;
	}

	public void setVm_Frequency(String vm_Frequency) {
		Vm_Frequency = vm_Frequency;
	}

	public String getPlantdown_Issueidentified() {
		return Plantdown_Issueidentified;
	}

	public void setPlantdown_Issueidentified(String plantdown_Issueidentified) {
		Plantdown_Issueidentified = plantdown_Issueidentified;
	}

	public String getPlantdown_Assigned() {
		return Plantdown_Assigned;
	}

	public void setPlantdown_Assigned(String plantdown_Assigned) {
		Plantdown_Assigned = plantdown_Assigned;
	}

	public String getPlantwarning_Issueidentified() {
		return Plantwarning_Issueidentified;
	}

	public void setPlantwarning_Issueidentified(String plantwarning_Issueidentified) {
		Plantwarning_Issueidentified = plantwarning_Issueidentified;
	}

	public String getPlantwarning_Assigned() {
		return Plantwarning_Assigned;
	}

	public void setPlantwarning_Assigned(String plantwarning_Assigned) {
		Plantwarning_Assigned = plantwarning_Assigned;
	}

	public String getPlantwarning_Resolved() {
		return Plantwarning_Resolved;
	}

	public void setPlantwarning_Resolved(String plantwarning_Resolved) {
		Plantwarning_Resolved = plantwarning_Resolved;
	}

	public String getEqugendown_Issueidentified() {
		return Equgendown_Issueidentified;
	}

	public void setEqugendown_Issueidentified(String equgendown_Issueidentified) {
		Equgendown_Issueidentified = equgendown_Issueidentified;
	}

	public String getEqugendown_Assigned() {
		return Equgendown_Assigned;
	}

	public void setEqugendown_Assigned(String equgendown_Assigned) {
		Equgendown_Assigned = equgendown_Assigned;
	}

	public String getEqugendown_Resolved() {
		return Equgendown_Resolved;
	}

	public void setEqugendown_Resolved(String equgendown_Resolved) {
		Equgendown_Resolved = equgendown_Resolved;
	}

	public String getEqugenwarning_Issueidentified() {
		return Equgenwarning_Issueidentified;
	}

	public void setEqugenwarning_Issueidentified(String equgenwarning_Issueidentified) {
		Equgenwarning_Issueidentified = equgenwarning_Issueidentified;
	}

	public String getEqugenwarning_Assigned() {
		return Equgenwarning_Assigned;
	}

	public void setEqugenwarning_Assigned(String equgenwarning_Assigned) {
		Equgenwarning_Assigned = equgenwarning_Assigned;
	}

	public String getEqugenwarning_Resolved() {
		return Equgenwarning_Resolved;
	}

	public void setEqugenwarning_Resolved(String equgenwarning_Resolved) {
		Equgenwarning_Resolved = equgenwarning_Resolved;
	}

	public String getEqugenoffline_Issueidentified() {
		return Equgenoffline_Issueidentified;
	}

	public void setEqugenoffline_Issueidentified(String equgenoffline_Issueidentified) {
		Equgenoffline_Issueidentified = equgenoffline_Issueidentified;
	}

	public String getEqugenoffline_Assigned() {
		return Equgenoffline_Assigned;
	}

	public void setEqugenoffline_Assigned(String equgenoffline_Assigned) {
		Equgenoffline_Assigned = equgenoffline_Assigned;
	}

	public String getEqugenoffline_Resolved() {
		return Equgenoffline_Resolved;
	}

	public void setEqugenoffline_Resolved(String equgenoffline_Resolved) {
		Equgenoffline_Resolved = equgenoffline_Resolved;
	}

	public String getEqunongendown_Issueidentified() {
		return Equnongendown_Issueidentified;
	}

	public void setEqunongendown_Issueidentified(String equnongendown_Issueidentified) {
		Equnongendown_Issueidentified = equnongendown_Issueidentified;
	}

	public String getEqunongendown_Assigned() {
		return Equnongendown_Assigned;
	}

	public void setEqunongendown_Assigned(String equnongendown_Assigned) {
		Equnongendown_Assigned = equnongendown_Assigned;
	}

	public String getEqunongendown_Resolved() {
		return Equnongendown_Resolved;
	}

	public void setEqunongendown_Resolved(String equnongendown_Resolved) {
		Equnongendown_Resolved = equnongendown_Resolved;
	}

	public String getEqunongenwarning_Issueidentified() {
		return Equnongenwarning_Issueidentified;
	}

	public void setEqunongenwarning_Issueidentified(String equnongenwarning_Issueidentified) {
		Equnongenwarning_Issueidentified = equnongenwarning_Issueidentified;
	}

	public String getEqunongenwarning_Assigned() {
		return Equnongenwarning_Assigned;
	}

	public void setEqunongenwarning_Assigned(String equnongenwarning_Assigned) {
		Equnongenwarning_Assigned = equnongenwarning_Assigned;
	}

	public String getEqunongenwarning_Resolved() {
		return Equnongenwarning_Resolved;
	}

	public void setEqunongenwarning_Resolved(String equnongenwarning_Resolved) {
		Equnongenwarning_Resolved = equnongenwarning_Resolved;
	}

	public String getEqunongenoffline_Issueidentified() {
		return Equnongenoffline_Issueidentified;
	}

	public void setEqunongenoffline_Issueidentified(String equnongenoffline_Issueidentified) {
		Equnongenoffline_Issueidentified = equnongenoffline_Issueidentified;
	}

	public String getEqunongenoffline_Assigned() {
		return Equnongenoffline_Assigned;
	}

	public void setEqunongenoffline_Assigned(String equnongenoffline_Assigned) {
		Equnongenoffline_Assigned = equnongenoffline_Assigned;
	}

	public String getEqunongenoffline_Resolved() {
		return Equnongenoffline_Resolved;
	}

	public void setEqunongenoffline_Resolved(String equnongenoffline_Resolved) {
		Equnongenoffline_Resolved = equnongenoffline_Resolved;
	}

	public Date getCommencementofwork() {
		return Commencementofwork;
	}

	public void setCommencementofwork(Date commencementofwork) {
		Commencementofwork = commencementofwork;
	}

	public String getPerformanceguaranteedamages() {
		return Performanceguaranteedamages;
	}

	public void setPerformanceguaranteedamages(String performanceguaranteedamages) {
		Performanceguaranteedamages = performanceguaranteedamages;
	}

	public String getSld() {
		return Sld;
	}

	public void setSld(String sld) {
		Sld = sld;
	}

	public String getPvsyst() {
		return Pvsyst;
	}

	public void setPvsyst(String pvsyst) {
		Pvsyst = pvsyst;
	}

	public String getConstructionLayout() {
		return ConstructionLayout;
	}

	public void setConstructionLayout(String constructionLayout) {
		ConstructionLayout = constructionLayout;
	}

	public String getWarrantyCertificate() {
		return WarrantyCertificate;
	}

	public void setWarrantyCertificate(String warrantyCertificate) {
		WarrantyCertificate = warrantyCertificate;
	}

	public String getInssuranceCertificate() {
		return InssuranceCertificate;
	}

	public void setInssuranceCertificate(String inssuranceCertificate) {
		InssuranceCertificate = inssuranceCertificate;
	}

	public String getApprovalCertificate() {
		return ApprovalCertificate;
	}

	public void setApprovalCertificate(String approvalCertificate) {
		ApprovalCertificate = approvalCertificate;
	}

	public String getPermits() {
		return Permits;
	}

	public void setPermits(String permits) {
		Permits = permits;
	}

	public String getTestCertificate() {
		return TestCertificate;
	}

	public void setTestCertificate(String testCertificate) {
		TestCertificate = testCertificate;
	}

	public String getServiceReport() {
		return ServiceReport;
	}

	public void setServiceReport(String serviceReport) {
		ServiceReport = serviceReport;
	}

	public String getIncidentReport() {
		return IncidentReport;
	}

	public void setIncidentReport(String incidentReport) {
		IncidentReport = incidentReport;
	}

	public String getPreventiveMaintenance() {
		return PreventiveMaintenance;
	}

	public void setPreventiveMaintenance(String preventiveMaintenance) {
		PreventiveMaintenance = preventiveMaintenance;
	}

	public String getCalibrationReport() {
		return CalibrationReport;
	}

	public void setCalibrationReport(String calibrationReport) {
		CalibrationReport = calibrationReport;
	}

	@Column(name = "decisionmaker")
	private String DecisionMaker;
	@Column(name = "dm_address")
	private String Dm_Address;
	@Column(name = "dm_city")
	private String Dm_City;
	@Column(name = "dm_state")
	private String Dm_State;
	@Column(name = "dm_country")
	private String Dm_Country;

	@Column(name = "dm_postalcode")
	private String Dm_Postalcode;
	@Column(name = "dm_emailid")
	private String Dm_Emailid;
	@Column(name = "dm_phoneno")
	private String Dm_Phoneno;

	@Column(name = "champion")
	private String Champion;
	@Column(name = "ch_address")
	private String Ch_Address;
	@Column(name = "ch_city")
	private String Ch_City;
	@Column(name = "ch_state")
	private String Ch_State;
	@Column(name = "ch_country")
	private String Ch_Country;

	@Column(name = "ch_postalcode")
	private String Ch_Postalcode;
	@Column(name = "ch_emailid")
	private String Ch_Emailid;
	@Column(name = "ch_phoneno")
	private String Ch_Phoneno;

	@Column(name = "roadblock")
	private String RoadBlock;
	@Column(name = "rd_address")
	private String Rd_Address;
	@Column(name = "rd_city")
	private String Rd_City;
	@Column(name = "rd_state")
	private String Rd_State;
	@Column(name = "rd_country")
	private String Rd_Country;

	@Column(name = "rd_postalcode")
	private String Rd_Postalcode;
	@Column(name = "rd_emailid")
	private String Rd_Emailid;
	@Column(name = "rd_phoneno")
	private String Rd_Phoneno;

	@Column(name = "keydeliverables")
	private String KeyDeliverables;
	@Column(name = "kd_address")
	private String Kd_Address;
	@Column(name = "kd_city")
	private String Kd_City;
	@Column(name = "kd_state")
	private String Kd_State;
	@Column(name = "kd_country")
	private String Kd_Country;

	@Column(name = "kd_postalcode")
	private String Kd_Postalcode;
	@Column(name = "kd_emailid")
	private String Kd_Emailid;
	@Column(name = "kd_phoneno")
	private String Kd_Phoneno;
	private String modulearea;

	public String getModuleArea() {
		return modulearea;
	}

	public void setModuleArea(String moduleArea) {
		modulearea = moduleArea;
	}

	public String getSecurity_Service() {
		return Security_Service;
	}

	public void setSecurity_Service(String security_Service) {
		Security_Service = security_Service;
	}

	public String getSecurity_Frequency() {
		return Security_Frequency;
	}

	public void setSecurity_Frequency(String security_Frequency) {
		Security_Frequency = security_Frequency;
	}

	public String getDecisionMaker() {
		return DecisionMaker;
	}

	public void setDecisionMaker(String decisionMaker) {
		DecisionMaker = decisionMaker;
	}

	public String getDm_Address() {
		return Dm_Address;
	}

	public void setDm_Address(String dm_Address) {
		Dm_Address = dm_Address;
	}

	public String getDm_City() {
		return Dm_City;
	}

	public void setDm_City(String dm_City) {
		Dm_City = dm_City;
	}

	public String getDm_State() {
		return Dm_State;
	}

	public void setDm_State(String dm_State) {
		Dm_State = dm_State;
	}

	public String getDm_Country() {
		return Dm_Country;
	}

	public void setDm_Country(String dm_Country) {
		Dm_Country = dm_Country;
	}

	public String getDm_Postalcode() {
		return Dm_Postalcode;
	}

	public void setDm_Postalcode(String dm_Postalcode) {
		Dm_Postalcode = dm_Postalcode;
	}

	public String getDm_Emailid() {
		return Dm_Emailid;
	}

	public void setDm_Emailid(String dm_Emailid) {
		Dm_Emailid = dm_Emailid;
	}

	public String getDm_Phoneno() {
		return Dm_Phoneno;
	}

	public void setDm_Phoneno(String dm_Phoneno) {
		Dm_Phoneno = dm_Phoneno;
	}

	public String getChampion() {
		return Champion;
	}

	public void setChampion(String champion) {
		Champion = champion;
	}

	public String getCh_Address() {
		return Ch_Address;
	}

	public void setCh_Address(String ch_Address) {
		Ch_Address = ch_Address;
	}

	public String getCh_City() {
		return Ch_City;
	}

	public void setCh_City(String ch_City) {
		Ch_City = ch_City;
	}

	public String getCh_State() {
		return Ch_State;
	}

	public void setCh_State(String ch_State) {
		Ch_State = ch_State;
	}

	public String getCh_Country() {
		return Ch_Country;
	}

	public void setCh_Country(String ch_Country) {
		Ch_Country = ch_Country;
	}

	public String getCh_Postalcode() {
		return Ch_Postalcode;
	}

	public void setCh_Postalcode(String ch_Postalcode) {
		Ch_Postalcode = ch_Postalcode;
	}

	public String getCh_Emailid() {
		return Ch_Emailid;
	}

	public void setCh_Emailid(String ch_Emailid) {
		Ch_Emailid = ch_Emailid;
	}

	public String getCh_Phoneno() {
		return Ch_Phoneno;
	}

	public void setCh_Phoneno(String ch_Phoneno) {
		Ch_Phoneno = ch_Phoneno;
	}

	public String getRoadBlock() {
		return RoadBlock;
	}

	public void setRoadBlock(String roadBlock) {
		RoadBlock = roadBlock;
	}

	public String getRd_Address() {
		return Rd_Address;
	}

	public void setRd_Address(String rd_Address) {
		Rd_Address = rd_Address;
	}

	public String getRd_City() {
		return Rd_City;
	}

	public void setRd_City(String rd_City) {
		Rd_City = rd_City;
	}

	public String getRd_State() {
		return Rd_State;
	}

	public void setRd_State(String rd_State) {
		Rd_State = rd_State;
	}

	public String getRd_Country() {
		return Rd_Country;
	}

	public void setRd_Country(String rd_Country) {
		Rd_Country = rd_Country;
	}

	public String getRd_Postalcode() {
		return Rd_Postalcode;
	}

	public void setRd_Postalcode(String rd_Postalcode) {
		Rd_Postalcode = rd_Postalcode;
	}

	public String getRd_Emailid() {
		return Rd_Emailid;
	}

	public void setRd_Emailid(String rd_Emailid) {
		Rd_Emailid = rd_Emailid;
	}

	public String getRd_Phoneno() {
		return Rd_Phoneno;
	}

	public void setRd_Phoneno(String rd_Phoneno) {
		Rd_Phoneno = rd_Phoneno;
	}

	public String getKeyDeliverables() {
		return KeyDeliverables;
	}

	public void setKeyDeliverables(String keyDeliverables) {
		KeyDeliverables = keyDeliverables;
	}

	public String getKd_Address() {
		return Kd_Address;
	}

	public void setKd_Address(String kd_Address) {
		Kd_Address = kd_Address;
	}

	public String getKd_City() {
		return Kd_City;
	}

	public void setKd_City(String kd_City) {
		Kd_City = kd_City;
	}

	public String getKd_State() {
		return Kd_State;
	}

	public void setKd_State(String kd_State) {
		Kd_State = kd_State;
	}

	public String getKd_Country() {
		return Kd_Country;
	}

	public void setKd_Country(String kd_Country) {
		Kd_Country = kd_Country;
	}

	public String getKd_Postalcode() {
		return Kd_Postalcode;
	}

	public void setKd_Postalcode(String kd_Postalcode) {
		Kd_Postalcode = kd_Postalcode;
	}

	public String getKd_Emailid() {
		return Kd_Emailid;
	}

	public void setKd_Emailid(String kd_Emailid) {
		Kd_Emailid = kd_Emailid;
	}

	public String getKd_Phoneno() {
		return Kd_Phoneno;
	}

	public void setKd_Phoneno(String kd_Phoneno) {
		Kd_Phoneno = kd_Phoneno;
	}

	@Column(name = "equipmentflag")
	private Integer EquipmentFlag;

	public Integer getEquipmentFlag() {
		return EquipmentFlag;
	}

	public void setEquipmentFlag(Integer equipmentFlag) {
		EquipmentFlag = equipmentFlag;
	}

	public String getCodText() {
		return CodText;
	}

	public void setCodText(String codText) {
		CodText = codText;
	}

	public Date getCod() {
		return Cod;
	}

	public void setCod(Date cod) {
		Cod = cod;
	}

	@Column(name = "correctivevisits_service")
	private String CorrectiveVisits_Service;

	@Column(name = "correctivevisits_frequency")
	private String CorrectiveVisits_Frequency;

	@Column(name = "assethealth_service")
	private String Assethealth_Service;

	@Column(name = "assethealth_frequency")
	private String Assethealth_Frequency;

	@Column(name = "insurance_service")
	private String Insurance_Service;

	@Column(name = "insurance_frequency")
	private String Insurance_Frequency;

	@Column(name = "warranty_service")
	private String Warranty_Service;

	@Column(name = "warranty_frequency")
	private String Warranty_Frequency;

	@Column(name = "completepreventive_service")
	private String CompletePreventive_Service;

	@Column(name = "completepreventive_frequency")
	private String CompletePreventive_Frequency;

	@Column(name = "equipmentrepair")
	private String EquipmentRepair;

	public String getCorrectiveVisits_Service() {
		return CorrectiveVisits_Service;
	}

	public void setCorrectiveVisits_Service(String correctiveVisits_Service) {
		CorrectiveVisits_Service = correctiveVisits_Service;
	}

	public String getCorrectiveVisits_Frequency() {
		return CorrectiveVisits_Frequency;
	}

	public void setCorrectiveVisits_Frequency(String correctiveVisits_Frequency) {
		CorrectiveVisits_Frequency = correctiveVisits_Frequency;
	}

	public String getAssethealth_Service() {
		return Assethealth_Service;
	}

	public void setAssethealth_Service(String assethealth_Service) {
		Assethealth_Service = assethealth_Service;
	}

	public String getAssethealth_Frequency() {
		return Assethealth_Frequency;
	}

	public void setAssethealth_Frequency(String assethealth_Frequency) {
		Assethealth_Frequency = assethealth_Frequency;
	}

	public String getInsurance_Service() {
		return Insurance_Service;
	}

	public void setInsurance_Service(String insurance_Service) {
		Insurance_Service = insurance_Service;
	}

	public String getInsurance_Frequency() {
		return Insurance_Frequency;
	}

	public void setInsurance_Frequency(String insurance_Frequency) {
		Insurance_Frequency = insurance_Frequency;
	}

	public String getWarranty_Service() {
		return Warranty_Service;
	}

	public void setWarranty_Service(String warranty_Service) {
		Warranty_Service = warranty_Service;
	}

	public String getWarranty_Frequency() {
		return Warranty_Frequency;
	}

	public void setWarranty_Frequency(String warranty_Frequency) {
		Warranty_Frequency = warranty_Frequency;
	}

	public String getCompletePreventive_Service() {
		return CompletePreventive_Service;
	}

	public void setCompletePreventive_Service(String completePreventive_Service) {
		CompletePreventive_Service = completePreventive_Service;
	}

	public String getCompletePreventive_Frequency() {
		return CompletePreventive_Frequency;
	}

	public void setCompletePreventive_Frequency(String completePreventive_Frequency) {
		CompletePreventive_Frequency = completePreventive_Frequency;
	}

	public String getEquipmentRepair() {
		return EquipmentRepair;
	}

	public void setEquipmentRepair(String equipmentRepair) {
		EquipmentRepair = equipmentRepair;
	}

}

