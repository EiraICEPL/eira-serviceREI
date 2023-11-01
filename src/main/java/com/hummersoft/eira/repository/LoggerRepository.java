package com.hummersoft.eira.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.Site;

@Repository
public interface LoggerRepository extends JpaRepository<Site, BigInteger> {
	/*
	 * @Query(value = "SELECT DISTINCT mip.standardname " +
	 * "FROM eampm.mparameterintegratedstandards mip " +
	 * "WHERE mip.standardname IS NOT NULL AND mip.standardname != '' " +
	 * "AND mip.dataloggerid IN (" + "    SELECT ms.dataloggerid_modbus " +
	 * "    FROM eampm.msite ms " + "    WHERE ms.siteId = :siteId " + "    UNION "
	 * + "    SELECT ms.dataloggerid_inverter " + "    FROM eampm.msite ms " +
	 * "    WHERE ms.siteId = :siteId " + "    UNION " +
	 * "    SELECT ms.dataloggerid_sensor " + "    FROM eampm.msite ms " +
	 * "    WHERE ms.siteId = :siteId" +")" +
	 * " and mip.standardname not in ('Status','ErrorCode')", nativeQuery = true)
	 * List<Object> findParameterNamesBySiteId(@Param("siteId") BigInteger siteId);
	 */
	
	
	@Query(value = "SELECT DISTINCT mip.standardname "
	        + "FROM eampm.mparameterintegratedstandards mip "
	        + "WHERE mip.standardname IS NOT NULL AND mip.standardname != '' "
	        + "AND mip.dataloggerid IN ("
	        + "    SELECT ms.dataloggerid_modbus FROM eampm.msite ms WHERE ms.siteId = :siteId "
	        + "    UNION "
	        + "    SELECT ms.dataloggerid_inverter FROM eampm.msite ms WHERE ms.siteId = :siteId "
	        + "    UNION "
	        + "    SELECT ms.dataloggerid_sensor FROM eampm.msite ms WHERE ms.siteId = :siteId "
	        + "    UNION "
	        + "    SELECT ms.dataloggerid_energymeter FROM eampm.msite ms WHERE ms.siteId = :siteId "
	        + "    UNION "
	        + "    SELECT ms.dataloggerid_scb FROM eampm.msite ms WHERE ms.siteId = :siteId "
	        + "    UNION "
	        + "    SELECT ms.dataloggerid_tracker FROM eampm.msite ms WHERE ms.siteId = :siteId"
	        + ") "
	        + "AND mip.standardname NOT IN ('Status', 'ErrorCode')", nativeQuery = true)
	List<Object> findParameterNamesBySiteId(@Param("siteId") BigInteger siteId);

}
