package com.hummersoft.eira.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "widgets", schema = "eampm")
public class Widgets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "widgetid", nullable = false)
	private BigInteger WidgetId;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "siteid")
	private int SiteId;

	@Column(name = "userid")
	private BigInteger userId;

	@Column(name = "charttype")
	private String ChartType;

	@Column(name = "equipment_ids", columnDefinition = "integer[]")
	private Integer[] Equipment_Ids;

	@Column(name = "measure_parameter", columnDefinition = "character varying[]")
	private String[] measureParameter;

	@Column(name = "range")
	private String Range;

	@Column(name = "widgetname")
	private String WidgetName;
	
	@Column(name="timeperiod")
	private String TimePeriod;
	
	@Column(name="activeflag")
	private Integer ActiveFlag;
	
	@Transient
	private Date fromDate;
	@Transient
	private Date toDate;
	
	@Transient
	private List<DailyGenerationTodayEnergyDTO> dailygenerationChartData;
	
	@Transient
	private List<EnergyPerformanceDTO> energyPerformanceChartData;
	
	@Transient
	private List<Map<String, String>> parameterList;

	public List<Map<String, String>> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Map<String, String>> parameterList) {
		this.parameterList = parameterList;
	}

	public List<DailyGenerationTodayEnergyDTO> getDailygenerationChartData() {
		return dailygenerationChartData;
	}

	public void setDailygenerationChartData(List<DailyGenerationTodayEnergyDTO> dailygenerationChartData) {
		this.dailygenerationChartData = dailygenerationChartData;
	}

	public List<EnergyPerformanceDTO> getEnergyPerformanceChartData() {
		return energyPerformanceChartData;
	}

	public void setEnergyPerformanceChartData(List<EnergyPerformanceDTO> energyPerformanceChartData) {
		this.energyPerformanceChartData = energyPerformanceChartData;
	}

	public String getWidgetName() {
		return WidgetName;
	}

	public void setWidgetName(String widgetName) {
		WidgetName = widgetName;
	}

	public String getRange() {
		return Range;
	}

	public void setRange(String range) {
		Range = range;
	}

	public String[] getMeasureParameter() {
		return measureParameter;
	}

	public void setMeasureParameter(String[] measureParameter) {
		this.measureParameter = measureParameter;
	}

	public BigInteger getWidgetId() {
		return WidgetId;
	}

	public void setWidgetId(BigInteger widgetId) {
		WidgetId = widgetId;
	}

	public int getSiteId() {
		return SiteId;
	}

	public void setSiteId(int siteId) {
		SiteId = siteId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getChartType() {
		return ChartType;
	}

	public void setChartType(String chartType) {
		ChartType = chartType;
	}

	public Integer[] getEquipment_Ids() {
		return Equipment_Ids;
	}

	public void setEquipment_Ids(Integer[] equipment_Ids) {
		Equipment_Ids = equipment_Ids;
	}

	public String getTimePeriod() {
		return TimePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		TimePeriod = timePeriod;
	}

	public Integer getActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		ActiveFlag = activeFlag;
	}

	
}