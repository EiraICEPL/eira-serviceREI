package com.hummersoft.eira.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.Equipment;

@Repository
public interface EquipmentEnergyMeterRepository extends JpaRepository<Equipment, Integer> {
	@Query(value = "SELECT e.equipmentid, e.equipmentcode, e.primaryserialnumber, e.capacity, e.customernaming, MAX(tt.timestamp) as timestamp " +
	        "FROM eampm.mequipment e " +
	        "INNER JOIN eampm.mequipmenttype et ON e.equipmenttypeid = et.equipmenttypeid " +
	        "INNER JOIN eampm.mequipmentcategory cat ON et.categoryid = cat.categoryid " +
	        "LEFT JOIN eampm.tdatatransaction tt ON e.equipmentid = tt.equipmentid " +
	        "WHERE e.siteid = :siteId " +
	        "AND cat.equipmentcategory IN ('Primary energy Meter', 'Secondary Energy Meter', 'Energy Meter') " +
	        "AND e.activeflag = 1 " +
	        "GROUP BY e.equipmentid, e.equipmentcode, e.primaryserialnumber, e.capacity, e.customernaming " +
	        "ORDER BY e.customernaming ASC", nativeQuery = true)
	List<Map<String, Object>> findEquipmentBySiteId(@Param("siteId") Integer siteId);

	 
	@Query(value = "SELECT e.equipmentid, e.equipmentcode, e.primaryserialnumber, e.customernaming, MAX(tt.timestamp) as timestamp " +
	        "FROM eampm.mequipment e " +
	        "INNER JOIN eampm.mequipmenttype et ON e.equipmenttypeid = et.equipmenttypeid " +
	        "INNER JOIN eampm.mequipmentcategory cat ON et.categoryid = cat.categoryid " +
	        "LEFT JOIN eampm.tdatatransaction tt ON e.equipmentid = tt.equipmentid " +
	        "WHERE e.siteid = :siteId " +
	        "AND cat.equipmentcategory IN ('WEATHRSTION') " +
	        "AND e.activeflag = 1 " +
	        "GROUP BY e.equipmentid, e.equipmentcode, e.primaryserialnumber, e.customernaming " +
	        "ORDER BY e.customernaming ASC", nativeQuery = true)
	List<Map<String, Object>> findWeathrstionBySite(@Param("siteId") Integer siteId);
}