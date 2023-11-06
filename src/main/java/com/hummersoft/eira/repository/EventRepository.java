package com.hummersoft.eira.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.EventDetail;


public interface EventRepository extends JpaRepository<EventDetail, Integer>{
	
	@Query(value="Select a.transactionid,a.eventid, a.errorid as errorid,a.equipmentid ,"
			+ "a.siteid,a.severity ,a.priority ,a.remarks ,  a.eventtimestamp ,  a.closedtimestamp,"
			+ "a.eventoccurrence ,a.eventstatus , a.activeflag ,a.creationdate,a.createdby,"
			+ "a.lastupdateddate ,a.lastupdatedby , a.lasteventtimestamp , SiteName, "
			+ "PrimarySerialNumber as EquipmentName,ErrorCode, ErrorMessage, EquipmentType, "
			+ "ErrorDescription,to_char(a.EventTimestamp,'DD-MM-YYYY HH24:MI') as EventTimestampText,"
			+ "to_char(a.ClosedTimestamp,'DD-MM-YYYY HH24:MI') as ClosedTimestampText,"
			+ "to_char(a.LastEventTimestamp,'DD-MM-YYYY HH24:MI') as LastEventTimestampText,"
			+ "c.CustomerNaming,c.Capacity from tEventDetail a left outer join mSite b "
			+ "on a.SiteId=b.SiteId left outer join mEquipment c on a.EquipmentId=c.EquipmentId left "
			+ "outer join mErrorCode d on a.ErrorId=d.ErrorId where a.ActiveFlag='1' and "
			+ "a.eventTimestamp >= date_trunc('day', now() - (60 * interval '1 day')) and "
			+ "a.EquipmentID =:equipmentId order by a.eventTimestamp desc",nativeQuery=true)
	List<EventDTO> findEventsByEquipment(@Param("equipmentId") int equipmentId);
	
	@Query(value="Select a.transactionid,a.eventid, a.errorid as errorid,a.equipmentid ,"
			+ "a.siteid,a.severity ,a.priority ,a.remarks ,  a.eventtimestamp ,  a.closedtimestamp,"
			+ "a.eventoccurrence ,a.eventstatus , a.activeflag ,a.creationdate,a.createdby,"
			+ "a.lastupdateddate ,a.lastupdatedby , a.lasteventtimestamp , SiteName, "
			+ "PrimarySerialNumber as EquipmentName,ErrorCode, ErrorMessage, EquipmentType, "
			+ "ErrorDescription,to_char(a.EventTimestamp,'DD-MM-YYYY HH24:MI') as EventTimestampText,"
			+ "to_char(a.ClosedTimestamp,'DD-MM-YYYY HH24:MI') as ClosedTimestampText,"
			+ "to_char(a.LastEventTimestamp,'DD-MM-YYYY HH24:MI') as LastEventTimestampText,"
			+ "c.CustomerNaming,c.Capacity from tEventDetail a left outer join mSite b "
			+ "on a.SiteId=b.SiteId left outer join mEquipment c on a.EquipmentId=c.EquipmentId left "
			+ "outer join mErrorCode d on a.ErrorId=d.ErrorId where a.ActiveFlag='1' and "
			+ "a.eventTimestamp >= date_trunc('day', now() - (60 * interval '1 day')) and "
			+ "a.siteId =:siteId order by a.eventTimestamp desc",nativeQuery=true)
	List<EventDTO> findEventsBySiteId(@Param("siteId") int siteId);
	
	@Query(value ="Select count(*) from tEventDetail where date_trunc('day',EventTimestamp)=date_trunc('day',now()) and ActiveFlag='1' and "
			+ "SiteID=:siteId", nativeQuery=true)
	int findTodayEventsBySiteId(@Param("siteId") int siteId);
	
	
	@Query(value ="Select count(*) from tEventDetail where date_trunc('day',EventTimestamp)=date_trunc('day',now()) and ActiveFlag='1' and "
			+ "SiteID in (select SiteId from mSiteMap where UserID= :userId and ActiveFlag=1)", nativeQuery=true)
	int findTodayEventsByUserId(@Param("userId") Long userId);
	
	
	
	@Query(value ="Select count(*) from tEventDetail where ActiveFlag='1' and "
			+ "SiteID in (select SiteId from mSiteMap where UserID= :userId and ActiveFlag=1)", nativeQuery=true)
	int findTotalEventsByUserId(@Param("userId") Long userId);
	
	@Query(value = "Select count(transactionid),teventdetail.SiteID, msite.sitename from tEventDetail, msite where teventdetail.siteid = msite.siteid "
			+ "and date_trunc('day',EventTimestamp) >= (CURRENT_DATE - INTERVAL '60 DAY') "
			+ "and  teventdetail.ActiveFlag='1' and "
			+ "teventdetail.SiteID in (select SiteId from mSiteMap where userID= :userId and ActiveFlag=1) "
			+ "group by teventdetail.SiteID,msite.sitename order by count(transactionid) desc limit 5",nativeQuery=true)
	List<Object> findTopFiveEventbyUserId(@Param("userId") Long userId);

	
	
	@Query(value="Select a.transactionid,a.eventid, a.errorid as errorid,a.equipmentid ,"
			+ "a.siteid,a.severity ,a.priority ,a.remarks ,  a.eventtimestamp ,  a.closedtimestamp,"
			+ "a.eventoccurrence ,a.eventstatus , a.activeflag ,a.creationdate,a.createdby,"
			+ "a.lastupdateddate ,a.lastupdatedby , a.lasteventtimestamp , SiteName, "
			+ "PrimarySerialNumber as EquipmentName,ErrorCode, ErrorMessage, EquipmentType, "
			+ "ErrorDescription,to_char(a.EventTimestamp,'DD-MM-YYYY HH24:MI') as EventTimestampText,"
			+ "to_char(a.ClosedTimestamp,'DD-MM-YYYY HH24:MI') as ClosedTimestampText,"
			+ "to_char(a.LastEventTimestamp,'DD-MM-YYYY HH24:MI') as LastEventTimestampText,"
			+ "c.CustomerNaming,c.Capacity from tEventDetail a left outer join mSite b "
			+ "on a.SiteId=b.SiteId left outer join mEquipment c on a.EquipmentId=c.EquipmentId left "
			+ "outer join mErrorCode d on a.ErrorId=d.ErrorId where a.ActiveFlag='1' and "
			+ "a.eventTimestamp between :fdate and :tdate and "
			+ "a.siteId =:siteId order by a.eventoccurrence desc",nativeQuery=true)
	List<EventDTO>  findTotalEventsBySiteIdforReports(@Param("siteId") int siteId,
			@Param("fdate") Timestamp fdate, @Param("tdate") Timestamp tdate);

}
