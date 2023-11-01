package com.hummersoft.eira.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.model.Analytics;

public interface EnergyPerformanceRepository extends JpaRepository<Analytics, Integer> {

	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT equipmentid,"
			+ "        DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp,"
			+ "        todayenergy - LAG(todayenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "    from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "equipmentid in :listEquipmentIds and " + "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp, equipmentid ,"
			+ "    difference AS todayenergy" + "   FROM Differences" + "		GROUP BY"
			+ " timestamp, equipmentid, difference" + "	order by timestamp asc")

	List<EnergyPerformanceDTO> getDgrTodayenergyValue(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp timestamp, @Param("timeStamp") Timestamp timeStamp,
			@Param("listEquipmentIds") List<Integer> listEquipmentIds);

	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT equipmentid,"
			+ "        DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp,"
			+ "        totalenergy - LAG(totalenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "    from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "equipmentid in :listEquipmentIds and " + "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp , equipmentid, "
			+ "    difference AS todayenergy" + "   FROM Differences" + "		GROUP BY"
			+ " timestamp, equipmentid, difference" + "	order by timestamp asc")

	List<EnergyPerformanceDTO> getDgrTotalEnergyValue(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp timestamp, @Param("timeStamp") Timestamp timeStamp,
			@Param("listEquipmentIds") List<Integer> listEquipmentIds);

	@Query(nativeQuery = true, value = "WITH MaxValues AS (SELECT DATE(timestamp) AS timestamp, equipmentid,"
			+ " MAX(todayenergy) AS todayenergy FROM eampm.tdatatransaction "
			+ " WHERE siteId = :siteid AND equipmentid IN :listEquipmentIds"
			+ " AND timestamp BETWEEN :timestamp AND :timeStamp "
			+ " GROUP BY DATE(timestamp), equipmentid )" + "SELECT timestamp, equipmentid, todayenergy"
			+ " FROM MaxValues ORDER BY timestamp, equipmentid")
	List<EnergyPerformanceDTO> getBarChartTodayenergyValue(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp fDate, @Param("timeStamp") Timestamp tdate, @Param("listEquipmentIds") List<Integer> listEquipmentIds);

	@Query(nativeQuery = true, value = "WITH MaxValues AS (SELECT DATE(timestamp) AS timestamp, equipmentid,"
			+ " MAX(totalenergy) AS max_totalenergy, MIN(totalenergy) AS min_totalenergy"
			+ "  FROM eampm.tdatatransaction" + "  WHERE siteid = :siteid AND equipmentid IN :listEquipmentIds"
			+ "  AND timestamp BETWEEN :timestamp AND :timeStamp "
			+ "  GROUP BY DATE(timestamp), equipmentid ) SELECT timestamp,"
			+ "  equipmentid, max_totalenergy - min_totalenergy AS todayenergy FROM MaxValues "
			+ "  ORDER BY timestamp, equipmentid")
	List<EnergyPerformanceDTO> getBarChartTotalEnergyValue(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp fDate, @Param("timeStamp") Timestamp tdate, @Param("listEquipmentIds") List<Integer> listEquipmentIds);

}