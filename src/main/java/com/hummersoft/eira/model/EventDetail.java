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
@Table(name = "teventdetail")
public class EventDetail implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionid")
	private Integer TransactionId;

	@Column(name = "eventid")
	private Integer EventId;

	@Column(name = "errorid")
	private Integer ErrorId;

	@Column(name = "equipmentid")
	private Integer EquipmentId;

	@Column(name = "siteid")
	private Integer SiteID;

	@Column(name = "severity")
	private Integer Severity;

	@Column(name = "priority")
	private Integer Priority;

	@Column(name = "remarks")
	private String Remarks;

	@Column(name = "eventtimestamp")
	private Date EventTimestamp;

	@Column(name = "lasteventtimestamp")
	private Date LastEventTimestamp;

	@Column(name = "closedtimestamp")
	private Date ClosedTimestamp;

	@Column(name = "eventoccurrence")
	private Integer EventOccurrence;

	@Column(name = "eventstatus")
	private Integer EventStatus;

	@Column(name = "activeflag")
	private Integer ActiveFlag;

	@Column(name = "creationdate")
	private Date CreationDate;

	@Column(name = "lastupdateddate")
	private Date LastUpdatedDate;

	@Column(name = "createdby", nullable = true)
	private Integer CreatedBy;

	@Column(name = "lastupdatedby", nullable = true)
	private Integer LastUpdatedBy;

	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public Integer getEventId() {
		return EventId;
	}

	public void setEventId(Integer eventId) {
		EventId = eventId;
	}

	public Integer getErrorId() {
		return ErrorId;
	}

	public void setErrorId(Integer errorId) {
		ErrorId = errorId;
	}

	public Integer getEquipmentId() {
		return EquipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		EquipmentId = equipmentId;
	}

	public Integer getSiteID() {
		return SiteID;
	}

	public void setSiteID(Integer siteId) {
		SiteID = siteId;
	}

	public Integer getSeverity() {
		return Severity;
	}

	public void setSeverity(Integer severity) {
		Severity = severity;
	}

	public Integer getPriority() {
		return Priority;
	}

	public void setPriority(Integer priority) {
		Priority = priority;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Date getEventTimestamp() {
		return EventTimestamp;
	}

	public void setEventTimestamp(Date eventTimestamp) {
		EventTimestamp = eventTimestamp;
	}

	public Date getClosedTimestamp() {
		return ClosedTimestamp;
	}

	public void setClosedTimestamp(Date closedTimestamp) {
		ClosedTimestamp = closedTimestamp;
	}

	public Integer getEventOccurrence() {
		return EventOccurrence;
	}

	public void setEventOccurrence(Integer eventOccurrence) {
		EventOccurrence = eventOccurrence;
	}

	public Integer getEventStatus() {
		return EventStatus;
	}

	public void setEventStatus(Integer eventStatus) {
		EventStatus = eventStatus;
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

	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public Integer getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(Integer createdBy) {
		CreatedBy = createdBy;
	}

	public Integer getLastUpdatedBy() {
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transient
	private String SiteName;

	@Transient
	private String LastEventTimestampText;

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	@Transient
	private String EquipmentName;

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	@Transient
	private String ErrorCode;

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	@Transient
	private String ErrorMessage;

	@Transient
	private String ErrorDescription;

	public String getErrorDescription() {
		return ErrorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	@Transient
	private String CreatedDateText;

	public String getCreatedDateText() {
		return CreatedDateText;
	}

	public void setCreatedDateText(String createdDateText) {
		CreatedDateText = createdDateText;
	}

	@Transient
	private String EventTimestampText;

	@Transient
	private String ClosedTimestampText;

	public String getEventTimestampText() {
		return EventTimestampText;
	}

	public void setEventTimestampText(String eventTimestampText) {
		EventTimestampText = eventTimestampText;
	}

	public String getLastEventTimestampText() {
		return LastEventTimestampText;
	}

	public Date getLastEventTimestamp() {
		return LastEventTimestamp;
	}

	public void setLastEventTimestamp(Date lastEventTimestamp) {
		LastEventTimestamp = lastEventTimestamp;
	}

	public void setLastEventTimestampText(String lastEventTimestampText) {
		LastEventTimestampText = lastEventTimestampText;
	}

	public String getClosedTimestampText() {
		return ClosedTimestampText;
	}

	public void setClosedTimestampText(String closedTimestampText) {
		ClosedTimestampText = closedTimestampText;
	}

	@Transient
	private String FromDate;

	@Transient
	private String ToDate;

	public String getFromDate() {
		return FromDate;
	}

	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}

	public String getToDate() {
		return ToDate;
	}

	public void setToDate(String toDate) {
		ToDate = toDate;
	}

	@Transient
	private String ScheduledOnText;

	public String getScheduledOnText() {
		return ScheduledOnText;
	}

	public void setScheduledOnText(String scheduledOnText) {
		ScheduledOnText = scheduledOnText;
	}

	public String getRescheduledOnText() {
		return RescheduledOnText;
	}

	public void setRescheduledOnText(String rescheduledOnText) {
		RescheduledOnText = rescheduledOnText;
	}

	@Transient
	private String RescheduledOnText;

	@Transient
	private String ClosedDateText;

	public String getClosedDateText() {
		return ClosedDateText;
	}

	public void setClosedDateText(String closedDateText) {
		ClosedDateText = closedDateText;
	}

	public String getScheduledDateText() {
		return ScheduledDateText;
	}

	public void setScheduledDateText(String scheduledDateText) {
		ScheduledDateText = scheduledDateText;
	}

	public String getStartedDateText() {
		return StartedDateText;
	}

	public void setStartedDateText(String startedDateText) {
		StartedDateText = startedDateText;
	}

	@Transient
	private String ScheduledDateText;

	@Transient
	private String StartedDateText;

	@Transient
	private String PriorityText;

	public String getPriorityText() {
		return PriorityText;
	}

	public void setPriorityText(String priorityText) {
		PriorityText = priorityText;
	}

	@Transient
	private String EquipmentType;

	public String getEquipmentType() {
		return EquipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		EquipmentType = equipmentType;
	}

	@Transient
	private String CustomerNaming;

	public String getCustomerNaming() {
		return CustomerNaming;
	}

	public void setCustomerNaming(String customerNaming) {
		CustomerNaming = customerNaming;
	}

	@Transient
	private String Capacity;

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

}
