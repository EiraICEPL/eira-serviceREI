package com.hummersoft.eira.dto;

import java.util.Date;


public interface EventDTO {
	
	 Integer getTransactionId();

	 Integer getEventId();

	 Integer getErrorId();

	 Integer getEquipmentId();
	
	 Integer getSiteID();
	
	 Integer getSeverity();

	 Integer getPriority();

	 String getRemarks();

	 Date getEventTimestamp();

	 Date getLastEventTimestamp();

	 Date getClosedTimestamp();

	 Integer getEventOccurrence();

	 Integer getEventStatus();

	 Integer getActiveFlag();


	 Date getCreationDate();

	 Date getLastUpdatedDate();

	 Integer getCreatedBy();

	 Integer getLastUpdatedBy();
	
	 String getSiteName();
	 String getLastEventTimestampText();

	 String getEquipmentName();
	
	 String getErrorCode();

	 String getErrorMessage();
	
	 String getErrorDescription();
	
	 String getCreatedDateText();

	 String getEventTimestampText();

	 String getClosedTimestampText();

	 String getFromDate();
	
	 String getToDate();

	 String getRescheduledOnText();
	
	 String getClosedDateText();

	 String getScheduledDateText();
	
	 String getStartedDateText();

	 String getPriorityText();

	 String getEquipmentType();

	 String getCustomerNaming();

	 String getCapacity();


}
