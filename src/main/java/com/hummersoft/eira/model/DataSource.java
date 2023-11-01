package com.hummersoft.eira.model;

import java.io.Serializable;
import java.util.Date;



import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="tdatasource")
public class DataSource implements Serializable{
	
	 private static final long serialVersionUID = -723583058586873479L;

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name = "transactionid")
	 private Integer TransactionId;
	
	
	 
	 @Column(name = "siteid")
	 private Integer SiteID;
	 
	 @Column(name = "equipmentid")
	 private Integer EquipmentID;
	 
	 @Column(name = "timestamp")
	 private Date Timestamp;
	 
	 @Column(name="dismandalflag")
	 private Integer DismandalFlag;
	 
	 public Integer getDismandalFlag() {
		return DismandalFlag;
	}

	public void setDismandalFlag(Integer dismandalFlag) {
		DismandalFlag = dismandalFlag;
	}

	@Column(name = "inputcurrent_01", nullable = true)
	 private Double InputCurrent_01;
	 
	 @Column(name = "inputcurrent_02", nullable = true)
	 private Double InputCurrent_02;
	 
	 @Column(name = "inputcurrent_03", nullable = true)
	 private Double InputCurrent_03;
	 
	 @Column(name = "inputcurrent_04", nullable = true)
	 private Double InputCurrent_04;
	
	 @Column(name = "inputcurrent_05", nullable = true)
	private Double InputCurrent_05;
	
	 @Column(name = "inputcurrent_06", nullable = true)
	private Double InputCurrent_06;
	
	 @Column(name = "inputcurrent_07", nullable = true)
	private Double InputCurrent_07;
	
	 @Column(name = "inputcurrent_08", nullable = true)
	private Double InputCurrent_08;
	
	 @Column(name = "inputcurrent_09", nullable = true)
	private Double InputCurrent_09;
	
	 @Column(name = "inputcurrent_10", nullable = true)
		private Double InputCurrent_10;
	 
	 @Column(name = "inputvoltage_01", nullable = true)
	 private Double InputVoltage_01;
	 
	 @Column(name = "inputvoltage_02", nullable = true)
	 private Double InputVoltage_02;
	 
	 @Column(name = "inputvoltage_03", nullable = true)
	 private Double InputVoltage_03;
	 
	 @Column(name = "inputvoltage_04", nullable = true)
	 private Double InputVoltage_04;
	 
	 @Column(name = "inputvoltage_05", nullable = true)
	 private Double InputVoltage_05;
	 
	 @Column(name = "inputvoltage_06", nullable = true)
	 private Double InputVoltage_06;
	 
	 @Column(name = "inputvoltage_07", nullable = true)
	 private Double InputVoltage_07;
	 
	 @Column(name = "inputvoltage_08", nullable = true)
	 private Double InputVoltage_08;
	 
	 @Column(name = "inputvoltage_09", nullable = true)
	 private Double InputVoltage_09;
	 
	 @Column(name = "inputvoltage_10", nullable = true)
	 private Double InputVoltage_10;
	 
	 
	 @Column(name = "inputpower_01", nullable = true)
	 private Double InputPower_01;
	 
	 @Column(name = "inputpower_02", nullable = true)
	 private Double InputPower_02;
	 
	 @Column(name = "inputpower_03", nullable = true)
	 private Double InputPower_03;
	 
	 @Column(name = "inputpower_04", nullable = true)
	 private Double InputPower_04;
	 
	 @Column(name = "inputpower_05", nullable = true)
	 private Double InputPower_05;
	 
	 @Column(name = "inputpower_06", nullable = true)
	 private Double InputPower_06;
	 
	 @Column(name = "inputpower_07", nullable = true)
	 private Double InputPower_07;
	 
	 @Column(name = "inputpower_08", nullable = true)
	 private Double InputPower_08;
	 
	 @Column(name = "inputpower_09", nullable = true)
	 private Double InputPower_09;
	 
	 @Column(name = "inputpower_10", nullable = true)
	 private Double InputPower_10;
	 
	 @Column(name = "phasecurrent", nullable = true)
	 private Double PhaseCurrent;
	 
	 @Column(name = "phasecurrent_l1", nullable = true)
	 private Double PhaseCurrent_L1;
	 
	 @Column(name = "phasecurrent_l2", nullable = true)
	 private Double PhaseCurrent_L2;
	 
	 @Column(name = "phasecurrent_l3", nullable = true)
	 private Double PhaseCurrent_L3;
	 
	 @Column(name = "phasevoltage", nullable = true)
	 private Double PhaseVoltage;
	 
	 @Column(name = "phasevoltage_l1", nullable = true)
	 private Double PhaseVoltage_L1;
	 
	 @Column(name = "phasevoltage_l2", nullable = true)
	 private Double PhaseVoltage_L2;
	 
	 @Column(name = "phasevoltage_l3", nullable = true)
	 private Double PhaseVoltage_L3;
	 
	 @Column(name = "apparentpower", nullable = true)
	 private Double ApparentPower;
	 
	 @Column(name = "activepower", nullable = true)
	 private Double ActivePower;
	 
	 @Column(name = "reactivepower", nullable = true)
	 private Double ReactivePower;
	 
	 @Column(name = "powerfactor", nullable = true)
	 private Double PowerFactor;
	 
	 @Column(name = "todayenergy", nullable = true)
	 private Double TodayEnergy;
	 
	 @Column(name = "totalenergy", nullable = true)
	 private Double TotalEnergy;
	 
	 @Column(name = "isolationresistance", nullable = true)
	 private Double IsolationResistance;
	 
	 @Column(name = "outputfrequency", nullable = true)
	 private Double OutputFrequency;
	 
	 @Column(name = "ambienttemperature", nullable = true)
	 private Double AmbientTemperature;
	 
	 @Column(name = "moduletemperature", nullable = true)
	 private Double ModuleTemperature;
	 
	 @Column(name = "invertertemperature", nullable = true)
	 private Double InverterTemperature;
	 
	 @Column(name = "windspeed", nullable = true)
	 private Double WindSpeed;
	 
	 @Column(name = "rainfall", nullable = true)
	 private Double Rainfall;
	 
	 @Column(name = "totalhourson", nullable = true)
	 private Double TotalHoursOn;
	 
	 @Column(name = "todayhourson", nullable = true)
	 private Double TodayHoursOn;
	 
	 @Column(name = "phasepowerbalancer", nullable = true)
	 private Double PhasePowerBalancer;
	 
	 @Column(name = "differentialcurrent", nullable = true)
	 private Double DifferentialCurrent;
	 
	 @Column(name = "status", nullable = true)
	 private Integer Status;
	 
	 @Column(name = "errorcode", nullable = true)
	 private String ErrorCode;
	 
	 @Column(name = "yesterdaytotalenergy", nullable = true)
	 private Double YesterdayTotalEnergy;
	 
	 @Column(name = "yesterdaytimestamp", nullable = true)
	 private Date YesterdayTimestamp;

	 public Integer getIrradiation() {
		return Irradiation;
	}

	public void setIrradiation(Integer irradiation) {
		Irradiation = irradiation;
	}

	public Date getLastDownTime() {
		return LastDownTime;
	}

	public void setLastDownTime(Date lastDownTime) {
		LastDownTime = lastDownTime;
	}

	@Column(name = "irradiation", nullable = true)
	 private Integer Irradiation;

	 @Column(name = "lastdowntime", nullable = true)
	 private Date LastDownTime;

	 @Column(name = "lastdatareceived", nullable = true)
	 private Date LastDataReceived;
	

	 
	public Date getLastDataReceived() {
		return LastDataReceived;
	}

	public void setLastDataReceived(Date lastDataReceived) {
		LastDataReceived = lastDataReceived;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="activeflag")
	private Integer ActiveFlag;	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate", insertable=false)
	private Date CreationDate;	
	
	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public Integer getSiteID() {
		return SiteID;
	}

	public void setSiteID(Integer siteID) {
		SiteID = siteID;
	}

	public Integer getEquipmentID() {
		return EquipmentID;
	}

	public void setEquipmentID(Integer equipmentID) {
		EquipmentID = equipmentID;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public Double getInputCurrent_01() {
		return InputCurrent_01;
	}

	public void setInputCurrent_01(Double inputCurrent_01) {
		InputCurrent_01 = inputCurrent_01;
	}

	public Double getInputCurrent_02() {
		return InputCurrent_02;
	}

	public void setInputCurrent_02(Double inputCurrent_02) {
		InputCurrent_02 = inputCurrent_02;
	}

	public Double getInputCurrent_03() {
		return InputCurrent_03;
	}

	public void setInputCurrent_03(Double inputCurrent_03) {
		InputCurrent_03 = inputCurrent_03;
	}

	public Double getInputCurrent_04() {
		return InputCurrent_04;
	}

	public void setInputCurrent_04(Double inputCurrent_04) {
		InputCurrent_04 = inputCurrent_04;
	}

	public Double getInputCurrent_05() {
		return InputCurrent_05;
	}

	public void setInputCurrent_05(Double inputCurrent_05) {
		InputCurrent_05 = inputCurrent_05;
	}

	public Double getInputCurrent_06() {
		return InputCurrent_06;
	}

	public void setInputCurrent_06(Double inputCurrent_06) {
		InputCurrent_06 = inputCurrent_06;
	}

	public Double getInputCurrent_07() {
		return InputCurrent_07;
	}

	public void setInputCurrent_07(Double inputCurrent_07) {
		InputCurrent_07 = inputCurrent_07;
	}

	public Double getInputCurrent_08() {
		return InputCurrent_08;
	}

	public void setInputCurrent_08(Double inputCurrent_08) {
		InputCurrent_08 = inputCurrent_08;
	}

	public Double getInputCurrent_09() {
		return InputCurrent_09;
	}

	public void setInputCurrent_09(Double inputCurrent_09) {
		InputCurrent_09 = inputCurrent_09;
	}

	public Double getInputCurrent_10() {
		return InputCurrent_10;
	}

	public void setInputCurrent_10(Double inputCurrent_10) {
		InputCurrent_10 = inputCurrent_10;
	}

	public Double getInputVoltage_01() {
		return InputVoltage_01;
	}

	public void setInputVoltage_01(Double inputVoltage_01) {
		InputVoltage_01 = inputVoltage_01;
	}

	public Double getInputVoltage_02() {
		return InputVoltage_02;
	}

	public void setInputVoltage_02(Double inputVoltage_02) {
		InputVoltage_02 = inputVoltage_02;
	}

	public Double getInputVoltage_03() {
		return InputVoltage_03;
	}

	public void setInputVoltage_03(Double inputVoltage_03) {
		InputVoltage_03 = inputVoltage_03;
	}

	public Double getInputVoltage_04() {
		return InputVoltage_04;
	}

	public void setInputVoltage_04(Double inputVoltage_04) {
		InputVoltage_04 = inputVoltage_04;
	}

	public Double getInputVoltage_05() {
		return InputVoltage_05;
	}

	public void setInputVoltage_05(Double inputVoltage_05) {
		InputVoltage_05 = inputVoltage_05;
	}

	public Double getInputVoltage_06() {
		return InputVoltage_06;
	}

	public void setInputVoltage_06(Double inputVoltage_06) {
		InputVoltage_06 = inputVoltage_06;
	}

	public Double getInputVoltage_07() {
		return InputVoltage_07;
	}

	public void setInputVoltage_07(Double inputVoltage_07) {
		InputVoltage_07 = inputVoltage_07;
	}

	public Double getInputVoltage_08() {
		return InputVoltage_08;
	}

	public void setInputVoltage_08(Double inputVoltage_08) {
		InputVoltage_08 = inputVoltage_08;
	}

	public Double getInputVoltage_09() {
		return InputVoltage_09;
	}

	public void setInputVoltage_09(Double inputVoltage_09) {
		InputVoltage_09 = inputVoltage_09;
	}

	public Double getInputVoltage_10() {
		return InputVoltage_10;
	}

	public void setInputVoltage_10(Double inputVoltage_10) {
		InputVoltage_10 = inputVoltage_10;
	}

	public Double getInputPower_01() {
		return InputPower_01;
	}

	public void setInputPower_01(Double inputPower_01) {
		InputPower_01 = inputPower_01;
	}

	public Double getInputPower_02() {
		return InputPower_02;
	}

	public void setInputPower_02(Double inputPower_02) {
		InputPower_02 = inputPower_02;
	}

	public Double getInputPower_03() {
		return InputPower_03;
	}

	public void setInputPower_03(Double inputPower_03) {
		InputPower_03 = inputPower_03;
	}

	public Double getInputPower_04() {
		return InputPower_04;
	}

	public void setInputPower_04(Double inputPower_04) {
		InputPower_04 = inputPower_04;
	}

	public Double getInputPower_05() {
		return InputPower_05;
	}

	public void setInputPower_05(Double inputPower_05) {
		InputPower_05 = inputPower_05;
	}

	public Double getInputPower_06() {
		return InputPower_06;
	}

	public void setInputPower_06(Double inputPower_06) {
		InputPower_06 = inputPower_06;
	}

	public Double getInputPower_07() {
		return InputPower_07;
	}

	public void setInputPower_07(Double inputPower_07) {
		InputPower_07 = inputPower_07;
	}

	public Double getInputPower_08() {
		return InputPower_08;
	}

	public void setInputPower_08(Double inputPower_08) {
		InputPower_08 = inputPower_08;
	}

	public Double getInputPower_09() {
		return InputPower_09;
	}

	public void setInputPower_09(Double inputPower_09) {
		InputPower_09 = inputPower_09;
	}

	public Double getInputPower_10() {
		return InputPower_10;
	}

	public void setInputPower_10(Double inputPower_10) {
		InputPower_10 = inputPower_10;
	}

	public Double getPhaseCurrent() {
		return PhaseCurrent;
	}

	public void setPhaseCurrent(Double phaseCurrent) {
		PhaseCurrent = phaseCurrent;
	}

	public Double getPhaseCurrent_L1() {
		return PhaseCurrent_L1;
	}

	public void setPhaseCurrent_L1(Double phaseCurrent_L1) {
		PhaseCurrent_L1 = phaseCurrent_L1;
	}

	public Double getPhaseCurrent_L2() {
		return PhaseCurrent_L2;
	}

	public void setPhaseCurrent_L2(Double phaseCurrent_L2) {
		PhaseCurrent_L2 = phaseCurrent_L2;
	}

	public Double getPhaseCurrent_L3() {
		return PhaseCurrent_L3;
	}

	public void setPhaseCurrent_L3(Double phaseCurrent_L3) {
		PhaseCurrent_L3 = phaseCurrent_L3;
	}

	public Double getPhaseVoltage() {
		return PhaseVoltage;
	}

	public void setPhaseVoltage(Double phaseVoltage) {
		PhaseVoltage = phaseVoltage;
	}

	public Double getPhaseVoltage_L1() {
		return PhaseVoltage_L1;
	}

	public void setPhaseVoltage_L1(Double phaseVoltage_L1) {
		PhaseVoltage_L1 = phaseVoltage_L1;
	}

	public Double getPhaseVoltage_L2() {
		return PhaseVoltage_L2;
	}

	public void setPhaseVoltage_L2(Double phaseVoltage_L2) {
		PhaseVoltage_L2 = phaseVoltage_L2;
	}

	public Double getPhaseVoltage_L3() {
		return PhaseVoltage_L3;
	}

	public void setPhaseVoltage_L3(Double phaseVoltage_L3) {
		PhaseVoltage_L3 = phaseVoltage_L3;
	}

	public Double getApparentPower() {
		return ApparentPower;
	}

	public void setApparentPower(Double apparentPower) {
		ApparentPower = apparentPower;
	}

	public Double getActivePower() {
		return ActivePower;
	}

	public void setActivePower(Double activePower) {
		ActivePower = activePower;
	}

	public Double getReactivePower() {
		return ReactivePower;
	}

	public void setReactivePower(Double reactivePower) {
		ReactivePower = reactivePower;
	}

	public Double getPowerFactor() {
		return PowerFactor;
	}

	public void setPowerFactor(Double powerFactor) {
		PowerFactor = powerFactor;
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

	public Double getIsolationResistance() {
		return IsolationResistance;
	}

	public void setIsolationResistance(Double isolationResistance) {
		IsolationResistance = isolationResistance;
	}

	public Double getOutputFrequency() {
		return OutputFrequency;
	}

	public void setOutputFrequency(Double outputFrequency) {
		OutputFrequency = outputFrequency;
	}

	public Double getAmbientTemperature() {
		return AmbientTemperature;
	}

	public void setAmbientTemperature(Double ambientTemperature) {
		AmbientTemperature = ambientTemperature;
	}

	public Double getModuleTemperature() {
		return ModuleTemperature;
	}

	public void setModuleTemperature(Double moduleTemperature) {
		ModuleTemperature = moduleTemperature;
	}

	public Double getInverterTemperature() {
		return InverterTemperature;
	}

	public void setInverterTemperature(Double inverterTemperature) {
		InverterTemperature = inverterTemperature;
	}

	public Double getWindSpeed() {
		return WindSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		WindSpeed = windSpeed;
	}

	public Double getRainfall() {
		return Rainfall;
	}

	public void setRainfall(Double rainfall) {
		Rainfall = rainfall;
	}

	public Double getTotalHoursOn() {
		return TotalHoursOn;
	}

	public void setTotalHoursOn(Double totalHoursOn) {
		TotalHoursOn = totalHoursOn;
	}

	public Double getTodayHoursOn() {
		return TodayHoursOn;
	}

	public void setTodayHoursOn(Double todayHoursOn) {
		TodayHoursOn = todayHoursOn;
	}

	public Double getPhasePowerBalancer() {
		return PhasePowerBalancer;
	}

	public void setPhasePowerBalancer(Double phasePowerBalancer) {
		PhasePowerBalancer = phasePowerBalancer;
	}

	public Double getDifferentialCurrent() {
		return DifferentialCurrent;
	}

	public void setDifferentialCurrent(Double differentialCurrent) {
		DifferentialCurrent = differentialCurrent;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public Double getYesterdayTotalEnergy() {
		return YesterdayTotalEnergy;
	}

	public void setYesterdayTotalEnergy(Double yesterdayTotalEnergy) {
		YesterdayTotalEnergy = yesterdayTotalEnergy;
	}

	public Date getYesterdayTimestamp() {
		return YesterdayTimestamp;
	}

	public void setYesterdayTimestamp(Date yesterdayTimestamp) {
		YesterdayTimestamp = yesterdayTimestamp;
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

	@Column(name="createdby" , nullable=true)
	private Integer CreatedBy;	
	
	
	@Column(name="lastupdateddate", nullable=true)
	private Date LastUpdatedDate;	
	
	@Column(name="lastupdatedby", nullable=true)
	private Integer LastUpdatedBy;	
	
	
	@Column(name="phasetophasevoltage_l1_l2", nullable=true)
	private Double PhaseToPhaseVoltage_L1_L2;
	
	@Column(name="phasetophasevoltage_l1_l3", nullable=true)
	private Double PhaseToPhaseVoltage_L1_L3;
	
	@Column(name="phasetophasevoltage_l3_l1", nullable=true)
	private Double PhaseToPhaseVoltage_L3_L1;
	
	
	
	@Column(name="heatsinktemp", nullable=true)
	private Double HeatSinkTemp;
	
	@Column(name="acbreakercount", nullable=true)
	private Double AcBreakerCount;
	
	@Column(name="dcbreakercount", nullable=true)
	private Double DcBreakerCount;
	
	
	
	@Column(name="apparentpower_l1", nullable=true)
	private Double ApparentPower_L1;
	
	@Column(name="apparentpower_l2", nullable=true)
	private Double ApparentPower_L2;
	
	@Column(name="apparentpower_l3", nullable=true)
	private Double ApparentPower_L3;
	
	@Column(name="activepower_l1", nullable=true)
	private Double ActivePower_L1;
	
	@Column(name="activepower_l2", nullable=true)
	private Double ActivePower_L2;
	
	@Column(name="activepower_l3", nullable=true)
	private Double ActivePower_L3;
	
	@Column(name="reactivepower_l1", nullable=true)
	private Double ReactivePower_L1;
	
	@Column(name="reactivepower_l2", nullable=true)
	private Double ReactivePower_L2;
	
	@Column(name="reactivepower_l3", nullable=true)
	private Double ReactivePower_L3;
	
	@Column(name="powerfactor_l1", nullable=true)
	private Double PowerFactor_L1;
	
	@Column(name="powerfactor_l2", nullable=true)
	private Double PowerFactor_L2;
	
	@Column(name="powerfactor_l3", nullable=true)
	private Double PowerFactor_L3;

	@Column(name="exporttotalenergy", nullable=true)
	private Double ExportTotalEnergy;
	
	@Column(name="importtotalenergy", nullable=true)
	private Double ImportTotalEnergy;
	
	@Column(name="exportactiveenergy", nullable=true)
	private Double ExportActiveEnergy;
	
	@Column(name="importactiveenergy", nullable=true)
	private Double ImportActiveEnergy;

	@Column(name="reactiveenergylag", nullable=true)
	private Double ReactiveEnergyLAG;
	
	@Column(name="reactiveenergylead", nullable=true)
	private Double ReactiveEnergyLEAD;
	
	@Column(name="winddirection", nullable=true)
	private Double WindDirection;
	
	@Column(name="gobalirradiance", nullable=true)
	private Double GobalIrradiance;
	
	@Column(name="humidity", nullable=true)
	private Double Humidity;
	
	@Column(name="trackerangle", nullable=true)
	private Double TrackerAngle;

	@Column(name="sunangle", nullable=true)
	private Double SunAngle;
	
	@Column(name="batteryvoltage", nullable=true)
	private Double BatteryVoltage;
	
	@Column(name="startroboposition", nullable=true)
	private Double StartRoboPosition;
	

	@Column(name="midroboposition", nullable=true)
	private Double MidRoboPosition;
	
	@Column(name="endroboposition", nullable=true)
	private Double EndRoboPosition;
	
	@Column(name="phasefrequency_l1", nullable=true)
	private Double PhaseFrequency_L1;
	
	@Column(name="phasefrequency_l2", nullable=true)
	private Double PhaseFrequency_L2;
	
	@Column(name="phasefrequency_l3", nullable=true)
	private Double PhaseFrequency_L3;
	
	@Column(name="phasepower_l1", nullable=true)
	private Double PhasePower_L1;
	
	@Column(name="phasepower_l2", nullable=true)
	private Double PhasePower_L2;
	
	@Column(name="phasepower_l3", nullable=true)
	private Double PhasePower_L3;
	
	@Column(name="phaseenergy_l1", nullable=true)
	private Double PhaseEnergy_L1;
	
	@Column(name="phaseenergy_l2", nullable=true)
	private Double PhaseEnergy_L2;
	
	@Column(name="phaseenergy_l3", nullable=true)
	private Double PhaseEnergy_L3;
	
	
	
	@Column(name="inputcurrent_11", nullable=true)
	private Double InputCurrent_11;
	
	@Column(name="inputcurrent_12", nullable=true)
	private Double InputCurrent_12;
	
	@Column(name="inputcurrent_13", nullable=true)
	private Double InputCurrent_13;
	
	@Column(name="inputcurrent_14", nullable=true)
	private Double InputCurrent_14;
	
	@Column(name="inputcurrent_15", nullable=true)
	private Double InputCurrent_15;
	
	@Column(name="inputcurrent_16", nullable=true)
	private Double InputCurrent_16;
	
	@Column(name="inputcurrent_17", nullable=true)
	private Double InputCurrent_17;
	
	@Column(name="inputcurrent_18", nullable=true)
	private Double InputCurrent_18;
	
	@Column(name="inputcurrent_19", nullable=true)
	private Double InputCurrent_19;
	
	@Column(name="inputcurrent_20", nullable=true)
	private Double InputCurrent_20;
	
	@Column(name="inputcurrent_21", nullable=true)
	private Double InputCurrent_21;
	
	@Column(name="inputcurrent_22", nullable=true)
	private Double InputCurrent_22;
	
	@Column(name="inputcurrent_23", nullable=true)
	private Double InputCurrent_23;
	
	@Column(name="inputcurrent_24", nullable=true)
	private Double InputCurrent_24;
	
	@Column(name="inputvoltage_11", nullable=true)
	private Double InputVoltage_11;
	
	@Column(name="inputvoltage_12", nullable=true)
	private Double InputVoltage_12;
	
	@Column(name="inputvoltage_13", nullable=true)
	private Double InputVoltage_13;
	
	@Column(name="inputvoltage_14", nullable=true)
	private Double InputVoltage_14;
	
	@Column(name="inputvoltage_15", nullable=true)
	private Double InputVoltage_15;
	
	@Column(name="inputvoltage_16", nullable=true)
	private Double InputVoltage_16;
	
	@Column(name="inputvoltage_17", nullable=true)
	private Double InputVoltage_17;
	
	@Column(name="inputvoltage_18", nullable=true)
	private Double InputVoltage_18;
	
	@Column(name="inputvoltage_19", nullable=true)
	private Double InputVoltage_19;
	
	@Column(name="inputvoltage_20", nullable=true)
	private Double InputVoltage_20;
	
	@Column(name="inputvoltage_21", nullable=true)
	private Double InputVoltage_21;
	
	@Column(name="inputvoltage_22", nullable=true)
	private Double InputVoltage_22;
	
	@Column(name="inputvoltage_23", nullable=true)
	private Double InputVoltage_23;
	
	@Column(name="inputvoltage_24", nullable=true)
	private Double InputVoltage_24;
	
	
	@Column(name="inputpower_11", nullable=true)
	private Double InputPower_11;
	
	@Column(name="inputpower_12", nullable=true)
	private Double InputPower_12;
	
	@Column(name="inputpower_13", nullable=true)
	private Double InputPower_13;
	
	@Column(name="inputpower_14", nullable=true)
	private Double InputPower_14;
	
	@Column(name="inputpower_15", nullable=true)
	private Double InputPower_15;
	
	@Column(name="inputpower_16", nullable=true)
	private Double InputPower_16;
	
	@Column(name="inputpower_17", nullable=true)
	private Double InputPower_17;
	
	@Column(name="inputpower_18", nullable=true)
	private Double InputPower_18;
	
	@Column(name="inputpower_19", nullable=true)
	private Double InputPower_19;
	
	@Column(name="inputpower_20", nullable=true)
	private Double InputPower_20;
	
	@Column(name="inputpower_21", nullable=true)
	private Double InputPower_21;
	
	public Double getPhaseToPhaseVoltage_L1_L2() {
		return PhaseToPhaseVoltage_L1_L2;
	}

	public void setPhaseToPhaseVoltage_L1_L2(Double phaseToPhaseVoltage_L1_L2) {
		PhaseToPhaseVoltage_L1_L2 = phaseToPhaseVoltage_L1_L2;
	}

	public Double getPhaseToPhaseVoltage_L1_L3() {
		return PhaseToPhaseVoltage_L1_L3;
	}

	public void setPhaseToPhaseVoltage_L1_L3(Double phaseToPhaseVoltage_L1_L3) {
		PhaseToPhaseVoltage_L1_L3 = phaseToPhaseVoltage_L1_L3;
	}

	public Double getPhaseToPhaseVoltage_L3_L1() {
		return PhaseToPhaseVoltage_L3_L1;
	}

	public void setPhaseToPhaseVoltage_L3_L1(Double phaseToPhaseVoltage_L3_L1) {
		PhaseToPhaseVoltage_L3_L1 = phaseToPhaseVoltage_L3_L1;
	}

	public Double getHeatSinkTemp() {
		return HeatSinkTemp;
	}

	public void setHeatSinkTemp(Double heatSinkTemp) {
		HeatSinkTemp = heatSinkTemp;
	}

	

	public Double getAcBreakerCount() {
		return AcBreakerCount;
	}

	public void setAcBreakerCount(Double acBreakerCount) {
		AcBreakerCount = acBreakerCount;
	}

	public Double getDcBreakerCount() {
		return DcBreakerCount;
	}

	public void setDcBreakerCount(Double dcBreakerCount) {
		DcBreakerCount = dcBreakerCount;
	}

	public Double getApparentPower_L1() {
		return ApparentPower_L1;
	}

	public void setApparentPower_L1(Double apparentPower_L1) {
		ApparentPower_L1 = apparentPower_L1;
	}

	public Double getApparentPower_L2() {
		return ApparentPower_L2;
	}

	public void setApparentPower_L2(Double apparentPower_L2) {
		ApparentPower_L2 = apparentPower_L2;
	}

	public Double getApparentPower_L3() {
		return ApparentPower_L3;
	}

	public void setApparentPower_L3(Double apparentPower_L3) {
		ApparentPower_L3 = apparentPower_L3;
	}

	public Double getActivePower_L1() {
		return ActivePower_L1;
	}

	public void setActivePower_L1(Double activePower_L1) {
		ActivePower_L1 = activePower_L1;
	}

	public Double getActivePower_L2() {
		return ActivePower_L2;
	}

	public void setActivePower_L2(Double activePower_L2) {
		ActivePower_L2 = activePower_L2;
	}

	public Double getActivePower_L3() {
		return ActivePower_L3;
	}

	public void setActivePower_L3(Double activePower_L3) {
		ActivePower_L3 = activePower_L3;
	}

	public Double getReactivePower_L1() {
		return ReactivePower_L1;
	}

	public void setReactivePower_L1(Double reactivePower_L1) {
		ReactivePower_L1 = reactivePower_L1;
	}

	public Double getReactivePower_L2() {
		return ReactivePower_L2;
	}

	public void setReactivePower_L2(Double reactivePower_L2) {
		ReactivePower_L2 = reactivePower_L2;
	}

	public Double getReactivePower_L3() {
		return ReactivePower_L3;
	}

	public void setReactivePower_L3(Double reactivePower_L3) {
		ReactivePower_L3 = reactivePower_L3;
	}

	public Double getPowerFactor_L1() {
		return PowerFactor_L1;
	}

	public void setPowerFactor_L1(Double powerFactor_L1) {
		PowerFactor_L1 = powerFactor_L1;
	}

	public Double getPowerFactor_L2() {
		return PowerFactor_L2;
	}

	public void setPowerFactor_L2(Double powerFactor_L2) {
		PowerFactor_L2 = powerFactor_L2;
	}

	public Double getPowerFactor_L3() {
		return PowerFactor_L3;
	}

	public void setPowerFactor_L3(Double powerFactor_L3) {
		PowerFactor_L3 = powerFactor_L3;
	}

	public Double getExportTotalEnergy() {
		return ExportTotalEnergy;
	}

	public void setExportTotalEnergy(Double exportTotalEnergy) {
		ExportTotalEnergy = exportTotalEnergy;
	}

	public Double getImportTotalEnergy() {
		return ImportTotalEnergy;
	}

	public void setImportTotalEnergy(Double importTotalEnergy) {
		ImportTotalEnergy = importTotalEnergy;
	}

	public Double getExportActiveEnergy() {
		return ExportActiveEnergy;
	}

	public void setExportActiveEnergy(Double exportActiveEnergy) {
		ExportActiveEnergy = exportActiveEnergy;
	}

	public Double getImportActiveEnergy() {
		return ImportActiveEnergy;
	}

	public void setImportActiveEnergy(Double importActiveEnergy) {
		ImportActiveEnergy = importActiveEnergy;
	}

	public Double getReactiveEnergyLAG() {
		return ReactiveEnergyLAG;
	}

	public void setReactiveEnergyLAG(Double reactiveEnergyLAG) {
		ReactiveEnergyLAG = reactiveEnergyLAG;
	}

	public Double getReactiveEnergyLEAD() {
		return ReactiveEnergyLEAD;
	}

	public void setReactiveEnergyLEAD(Double reactiveEnergyLEAD) {
		ReactiveEnergyLEAD = reactiveEnergyLEAD;
	}

	public Double getWindDirection() {
		return WindDirection;
	}

	public void setWindDirection(Double windDirection) {
		WindDirection = windDirection;
	}

	public Double getGobalIrradiance() {
		return GobalIrradiance;
	}

	public void setGobalIrradiance(Double gobalIrradiance) {
		GobalIrradiance = gobalIrradiance;
	}

	public Double getHumidity() {
		return Humidity;
	}

	public void setHumidity(Double humidity) {
		Humidity = humidity;
	}

	public Double getTrackerAngle() {
		return TrackerAngle;
	}

	public void setTrackerAngle(Double trackerAngle) {
		TrackerAngle = trackerAngle;
	}

	public Double getSunAngle() {
		return SunAngle;
	}

	public void setSunAngle(Double sunAngle) {
		SunAngle = sunAngle;
	}

	public Double getBatteryVoltage() {
		return BatteryVoltage;
	}

	public void setBatteryVoltage(Double batteryVoltage) {
		BatteryVoltage = batteryVoltage;
	}

	public Double getStartRoboPosition() {
		return StartRoboPosition;
	}

	public void setStartRoboPosition(Double startRoboPosition) {
		StartRoboPosition = startRoboPosition;
	}

	public Double getMidRoboPosition() {
		return MidRoboPosition;
	}

	public void setMidRoboPosition(Double midRoboPosition) {
		MidRoboPosition = midRoboPosition;
	}

	public Double getEndRoboPosition() {
		return EndRoboPosition;
	}

	public void setEndRoboPosition(Double endRoboPosition) {
		EndRoboPosition = endRoboPosition;
	}

	public Double getPhaseFrequency_L1() {
		return PhaseFrequency_L1;
	}

	public void setPhaseFrequency_L1(Double phaseFrequency_L1) {
		PhaseFrequency_L1 = phaseFrequency_L1;
	}

	public Double getPhaseFrequency_L2() {
		return PhaseFrequency_L2;
	}

	public void setPhaseFrequency_L2(Double phaseFrequency_L2) {
		PhaseFrequency_L2 = phaseFrequency_L2;
	}

	public Double getPhaseFrequency_L3() {
		return PhaseFrequency_L3;
	}

	public void setPhaseFrequency_L3(Double phaseFrequency_L3) {
		PhaseFrequency_L3 = phaseFrequency_L3;
	}

	public Double getPhasePower_L1() {
		return PhasePower_L1;
	}

	public void setPhasePower_L1(Double phasePower_L1) {
		PhasePower_L1 = phasePower_L1;
	}

	public Double getPhasePower_L2() {
		return PhasePower_L2;
	}

	public void setPhasePower_L2(Double phasePower_L2) {
		PhasePower_L2 = phasePower_L2;
	}

	public Double getPhasePower_L3() {
		return PhasePower_L3;
	}

	public void setPhasePower_L3(Double phasePower_L3) {
		PhasePower_L3 = phasePower_L3;
	}

	public Double getPhaseEnergy_L1() {
		return PhaseEnergy_L1;
	}

	public void setPhaseEnergy_L1(Double phaseEnergy_L1) {
		PhaseEnergy_L1 = phaseEnergy_L1;
	}

	public Double getPhaseEnergy_L2() {
		return PhaseEnergy_L2;
	}

	public void setPhaseEnergy_L2(Double phaseEnergy_L2) {
		PhaseEnergy_L2 = phaseEnergy_L2;
	}

	public Double getPhaseEnergy_L3() {
		return PhaseEnergy_L3;
	}

	public void setPhaseEnergy_L3(Double phaseEnergy_L3) {
		PhaseEnergy_L3 = phaseEnergy_L3;
	}

	public Double getInputCurrent_11() {
		return InputCurrent_11;
	}

	public void setInputCurrent_11(Double inputCurrent_11) {
		InputCurrent_11 = inputCurrent_11;
	}

	public Double getInputCurrent_12() {
		return InputCurrent_12;
	}

	public void setInputCurrent_12(Double inputCurrent_12) {
		InputCurrent_12 = inputCurrent_12;
	}

	public Double getInputCurrent_13() {
		return InputCurrent_13;
	}

	public void setInputCurrent_13(Double inputCurrent_13) {
		InputCurrent_13 = inputCurrent_13;
	}

	public Double getInputCurrent_14() {
		return InputCurrent_14;
	}

	public void setInputCurrent_14(Double inputCurrent_14) {
		InputCurrent_14 = inputCurrent_14;
	}

	public Double getInputCurrent_15() {
		return InputCurrent_15;
	}

	public void setInputCurrent_15(Double inputCurrent_15) {
		InputCurrent_15 = inputCurrent_15;
	}

	public Double getInputCurrent_16() {
		return InputCurrent_16;
	}

	public void setInputCurrent_16(Double inputCurrent_16) {
		InputCurrent_16 = inputCurrent_16;
	}

	public Double getInputCurrent_17() {
		return InputCurrent_17;
	}

	public void setInputCurrent_17(Double inputCurrent_17) {
		InputCurrent_17 = inputCurrent_17;
	}

	public Double getInputCurrent_18() {
		return InputCurrent_18;
	}

	public void setInputCurrent_18(Double inputCurrent_18) {
		InputCurrent_18 = inputCurrent_18;
	}

	public Double getInputCurrent_19() {
		return InputCurrent_19;
	}

	public void setInputCurrent_19(Double inputCurrent_19) {
		InputCurrent_19 = inputCurrent_19;
	}

	public Double getInputCurrent_20() {
		return InputCurrent_20;
	}

	public void setInputCurrent_20(Double inputCurrent_20) {
		InputCurrent_20 = inputCurrent_20;
	}

	public Double getInputCurrent_21() {
		return InputCurrent_21;
	}

	public void setInputCurrent_21(Double inputCurrent_21) {
		InputCurrent_21 = inputCurrent_21;
	}

	public Double getInputCurrent_22() {
		return InputCurrent_22;
	}

	public void setInputCurrent_22(Double inputCurrent_22) {
		InputCurrent_22 = inputCurrent_22;
	}

	public Double getInputCurrent_23() {
		return InputCurrent_23;
	}

	public void setInputCurrent_23(Double inputCurrent_23) {
		InputCurrent_23 = inputCurrent_23;
	}

	public Double getInputCurrent_24() {
		return InputCurrent_24;
	}

	public void setInputCurrent_24(Double inputCurrent_24) {
		InputCurrent_24 = inputCurrent_24;
	}

	public Double getInputVoltage_11() {
		return InputVoltage_11;
	}

	public void setInputVoltage_11(Double inputVoltage_11) {
		InputVoltage_11 = inputVoltage_11;
	}

	public Double getInputVoltage_12() {
		return InputVoltage_12;
	}

	public void setInputVoltage_12(Double inputVoltage_12) {
		InputVoltage_12 = inputVoltage_12;
	}

	public Double getInputVoltage_13() {
		return InputVoltage_13;
	}

	public void setInputVoltage_13(Double inputVoltage_13) {
		InputVoltage_13 = inputVoltage_13;
	}

	public Double getInputVoltage_14() {
		return InputVoltage_14;
	}

	public void setInputVoltage_14(Double inputVoltage_14) {
		InputVoltage_14 = inputVoltage_14;
	}

	public Double getInputVoltage_15() {
		return InputVoltage_15;
	}

	public void setInputVoltage_15(Double inputVoltage_15) {
		InputVoltage_15 = inputVoltage_15;
	}

	public Double getInputVoltage_16() {
		return InputVoltage_16;
	}

	public void setInputVoltage_16(Double inputVoltage_16) {
		InputVoltage_16 = inputVoltage_16;
	}

	public Double getInputVoltage_17() {
		return InputVoltage_17;
	}

	public void setInputVoltage_17(Double inputVoltage_17) {
		InputVoltage_17 = inputVoltage_17;
	}

	public Double getInputVoltage_18() {
		return InputVoltage_18;
	}

	public void setInputVoltage_18(Double inputVoltage_18) {
		InputVoltage_18 = inputVoltage_18;
	}

	public Double getInputVoltage_19() {
		return InputVoltage_19;
	}

	public void setInputVoltage_19(Double inputVoltage_19) {
		InputVoltage_19 = inputVoltage_19;
	}

	public Double getInputVoltage_20() {
		return InputVoltage_20;
	}

	public void setInputVoltage_20(Double inputVoltage_20) {
		InputVoltage_20 = inputVoltage_20;
	}

	public Double getInputVoltage_21() {
		return InputVoltage_21;
	}

	public void setInputVoltage_21(Double inputVoltage_21) {
		InputVoltage_21 = inputVoltage_21;
	}

	public Double getInputVoltage_22() {
		return InputVoltage_22;
	}

	public void setInputVoltage_22(Double inputVoltage_22) {
		InputVoltage_22 = inputVoltage_22;
	}

	public Double getInputVoltage_23() {
		return InputVoltage_23;
	}

	public void setInputVoltage_23(Double inputVoltage_23) {
		InputVoltage_23 = inputVoltage_23;
	}

	public Double getInputVoltage_24() {
		return InputVoltage_24;
	}

	public void setInputVoltage_24(Double inputVoltage_24) {
		InputVoltage_24 = inputVoltage_24;
	}

	public Double getInputPower_11() {
		return InputPower_11;
	}

	public void setInputPower_11(Double inputPower_11) {
		InputPower_11 = inputPower_11;
	}

	public Double getInputPower_12() {
		return InputPower_12;
	}

	public void setInputPower_12(Double inputPower_12) {
		InputPower_12 = inputPower_12;
	}

	public Double getInputPower_13() {
		return InputPower_13;
	}

	public void setInputPower_13(Double inputPower_13) {
		InputPower_13 = inputPower_13;
	}

	public Double getInputPower_14() {
		return InputPower_14;
	}

	public void setInputPower_14(Double inputPower_14) {
		InputPower_14 = inputPower_14;
	}

	public Double getInputPower_15() {
		return InputPower_15;
	}

	public void setInputPower_15(Double inputPower_15) {
		InputPower_15 = inputPower_15;
	}

	public Double getInputPower_16() {
		return InputPower_16;
	}

	public void setInputPower_16(Double inputPower_16) {
		InputPower_16 = inputPower_16;
	}

	public Double getInputPower_17() {
		return InputPower_17;
	}

	public void setInputPower_17(Double inputPower_17) {
		InputPower_17 = inputPower_17;
	}

	public Double getInputPower_18() {
		return InputPower_18;
	}

	public void setInputPower_18(Double inputPower_18) {
		InputPower_18 = inputPower_18;
	}

	public Double getInputPower_19() {
		return InputPower_19;
	}

	public void setInputPower_19(Double inputPower_19) {
		InputPower_19 = inputPower_19;
	}

	public Double getInputPower_20() {
		return InputPower_20;
	}

	public void setInputPower_20(Double inputPower_20) {
		InputPower_20 = inputPower_20;
	}

	public Double getInputPower_21() {
		return InputPower_21;
	}

	public void setInputPower_21(Double inputPower_21) {
		InputPower_21 = inputPower_21;
	}

	public Double getInputPower_22() {
		return InputPower_22;
	}

	public void setInputPower_22(Double inputPower_22) {
		InputPower_22 = inputPower_22;
	}

	public Double getInputPower_23() {
		return InputPower_23;
	}

	public void setInputPower_23(Double inputPower_23) {
		InputPower_23 = inputPower_23;
	}

	public Double getInputPower_24() {
		return InputPower_24;
	}

	public void setInputPower_24(Double inputPower_24) {
		InputPower_24 = inputPower_24;
	}

	@Column(name="inputpower_22", nullable=true)
	private Double InputPower_22;
	
	@Column(name="inputpower_23", nullable=true)
	private Double InputPower_23;
	
	@Column(name="inputpower_24", nullable=true)
	private Double InputPower_24;
	
		
}

