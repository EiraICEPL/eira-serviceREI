package com.hummersoft.eira.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.model.Equipment;


public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{ 
	
		
	@Query (value = "select * from mequipment where activeflag=1 and dismandalflag= 0 and "
			+ "siteid= :siteId and equipmenttypeid in (select equipmenttypeid from mequipmenttype where activeflag = '1' and "
			+ "categoryid in (select categoryid from mequipmentcategory where activeflag ='1' and "
			+ "equipmentcategory in ('CENTRLINVRTR','STRINGINVRTR'))) ", nativeQuery=true)
	List<Equipment> getInvEquipmenList(@Param("siteId") int siteId);
	
	
	
	@Query (value = "select * from mequipment where activeflag='1' and dismandalflag=0 and prodflag=1 and "
			+ "siteid= :siteId and equipmenttypeid in (select equipmenttypeid from mequipmenttype where activeflag = '1' and "
			+ "categoryid in (select categoryid from mequipmentcategory where activeflag ='1' and "
			+ "equipmentcategory in ('CENTRLINVRTR','STRINGINVRTR','Primary Energy Meter','Secondary Energy Meter',"
			+ "'WEATHRSTION','STRINGCOMBINER','Tracker Sensor'))) ", nativeQuery=true)
	List<Equipment> getEquipmenList(@Param("siteId") int siteId);
	
	
	@Query(value="select mequip.siteid as SiteId,mequip.equipmentid as EquipmentId,mequip.capacity as Capacity,"
			+ "mequip.equipmentcode as EquipmentCode,mequip.customerreference as CutomerRef,"
			+ "mequip.displayname as DisplayName,mequip.primaryserialnumber as PrimarySerialNo,mequip.customernaming as CustomerNaming,mequip.dismandalflag as DismandalFlag,"
			+ "cat.equipmentcategory  as Category from mequipment mequip "
			+ "left outer join mEquipmentType etype on etype.EquipmentTypeID=mequip.EquipmentTypeID and etype.ActiveFlag='1' "
			+ "left outer join mEquipmentCategory cat on etype.CategoryId =cat.CategoryId  and cat.activeflag='1' "
			+ "where mequip.siteid=:siteId and mequip.ActiveFlag='1' and cat.equipmentcategory in "
			+ "('CENTRLINVRTR','STRINGINVRTR','Primary Energy Meter','Secondary Energy Meter','STRINGCOMBINER','Tracker Sensor') order by mequip.equipmentid", nativeQuery=true)
	List<EquipmentDTO> getAllEquipmenList(@Param("siteId") int siteId);

	@Query(value = "select * from mequipment where equipmentid=:equipmentId",nativeQuery=true)
	Equipment getEquipmentbyequipid(@Param("equipmentId") int equipmentId);
	
	
	 @Query(value = "SELECT e.equipmentid, e.customernaming " +
	            "FROM eampm.mequipment e " +
	            "INNER JOIN eampm.mequipmenttype et ON e.equipmenttypeid = et.equipmenttypeid " +
	            "WHERE e.siteid = :siteId " +
	            "AND et.categoryid IN (4, 15, 18, 47) " +
	            "AND e.activeflag = 1 order by customernaming asc", nativeQuery = true)
	    List<Object[]> findEquipmentBySiteId(@Param("siteId") BigInteger siteId);
	    
	    
	    @Query(value ="select count (mequip.equipmentid) ,etype.equipmenttype,cat.equipmentcategory from mequipment mequip "
		 		+ "left outer join mEquipmentType etype on etype.EquipmentTypeID=mequip.EquipmentTypeID and etype.ActiveFlag='1' "
		 		+ "left outer join mEquipmentCategory cat on etype.CategoryId =cat.CategoryId  and cat.activeflag='1' "
		 		+ "where mequip.siteid=1189 and mequip.ActiveFlag='1' and cat.equipmentcategory in "
		 		+ "('CENTRLINVRTR','STRINGINVRTR','Primary Energy Meter','Secondary Energy Meter','STRINGCOMBINER','Tracker Sensor',"
		 		+ " 'WEATHRSTION') group by etype.equipmenttype,cat.equipmentcategory",nativeQuery=true)
		 List<Object[]> findEquipmentStatistics(@Param("siteId") BigInteger siteId);
	    
}
