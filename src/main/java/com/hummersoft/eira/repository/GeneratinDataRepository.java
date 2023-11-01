package com.hummersoft.eira.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;
import com.hummersoft.eira.model.Analytics;

@Repository
public interface GeneratinDataRepository extends JpaRepository<Analytics, String> {

	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT siteid,"
			+ "        DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp," + " irradiation as irradiation, "
			+ "        todayenergy - LAG(todayenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "    from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp ,"
			+ " MAX(irradiation) as irradiation,  SUM(difference) AS todayenergy" + "   FROM Differences"
			+ "		GROUP BY" + " timestamp  " + "	order by timestamp asc")

	List<DailyGenerationTodayEnergyDTO> getDgrTodayenergyValue(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp date, @Param("timeStamp") Timestamp date2);

	@Query(nativeQuery = true, value = "select        msite.todayenergyflag as todayenergyflag "
			+ "  from \"eampm\".msite " + "		where msite.siteid=:siteid")
	int getEnergyFlag(@Param("siteid") int siteid);

	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT siteid," + "  irradiation as irradiation,"
			+ "  DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp,"
			+ "  totalenergy - LAG(totalenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "  from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp ,"
			+ "  MAX(irradiation) as irradiation,  SUM(difference) AS todayenergy" + "   FROM Differences"
			+ "		GROUP BY" + " timestamp" + "	order by timestamp asc")
	List<DailyGenerationTodayEnergyDTO> getDgrTotalEnergyValue(@Param("siteid") int siteId,
			@Param("timestamp") Timestamp fdate, @Param("timeStamp") Timestamp tdate);

	@Query(nativeQuery = true, value = "WITH MaxValues AS ( SELECT DATE(tdatatransaction.timestamp) AS timestamp, equipmentid, MAX(todayenergy) AS todayenergy, "
			+ "  MAX(irradiation) AS irradiation" + "  FROM eampm.tdatatransaction  WHERE siteId = :siteId"
			+ "  AND timestamp BETWEEN :timeStamp AND :timestamp" + "    GROUP BY DATE(timestamp), equipmentid " + "),"
			+ "  ChartValues AS ( SELECT timestamp, SUM(todayenergy) AS todayenergy, MAX(irradiation) AS irradiation"
			+ " FROM MaxValues  GROUP BY timestamp" + ")"
			+ "  SELECT timestamp,todayenergy, irradiation FROM ChartValues ORDER BY timestamp")
	List<DailyGenerationTodayEnergyDTO> getDgrBarChartTodayEnergyValue(@Param("siteId") int siteId,
			@Param("timeStamp") Timestamp fDate, @Param("timestamp") Timestamp tdate);

	@Query(nativeQuery = true, value = "WITH MaxValues AS (SELECT DATE(tdatatransaction.timestamp) AS timestamp,"
			+ "  equipmentid, MAX(tdatatransaction.totalenergy) AS max_totalenergy,"
			+ "  MIN(tdatatransaction.totalenergy) AS min_totalenergy, MAX(irradiation) as irradiation FROM eampm.tdatatransaction"
			+ "  WHERE siteId = :siteId  AND timestamp BETWEEN :timeStamp AND :timestamp"
			+ "  GROUP BY DATE(timestamp), equipmentid )," + "  DailyDifferences AS (SELECT timestamp,"
			+ "  SUM(max_totalenergy - min_totalenergy) AS todayenergy, MAX(irradiation) as irradiation " + "    FROM MaxValues GROUP BY timestamp)"
			+ "  SELECT timestamp, todayenergy, irradiation" + " FROM DailyDifferences " + "ORDER BY timestamp")
	List<DailyGenerationTodayEnergyDTO> getDgrBarChartTotalEnergyValue(@Param("siteId") int siteId,
			@Param("timeStamp") Timestamp fDate, @Param("timestamp") Timestamp tdate);
	
	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT siteid,"
			+ "        DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp," 
			+ "        todayenergy - LAG(todayenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "    from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp ,"
			+ " SUM(difference) AS todayenergy" + "   FROM Differences"
			+ "		GROUP BY" + " timestamp  " + "	order by timestamp asc")

	List<DailyGenerationTodayEnergyDTO> getTodatEnergyValueForSpecificYield(@Param("siteid") int siteid,
			@Param("timestamp") Timestamp date, @Param("timeStamp") Timestamp date2);
//	(int siteId, Timestamp fDate, Timestamp tdate);
	
	@Query(nativeQuery = true, value = "WITH Differences AS (" + "    SELECT siteid," 
			+ "  DATE_TRUNC('minute',tdatatransaction.timestamp) AS timestamp,"
			+ "  totalenergy - LAG(totalenergy) OVER (PARTITION BY equipmentid ORDER BY timestamp) AS difference"
			+ "  from \"eampm\".tdatatransaction" + "	where siteid=:siteid and  "
			+ "timestamp between :timestamp and :timeStamp " + ") "
			+ "SELECT timestamp + INTERVAL '5 hours 30 minutes' AS timestamp ,"
			+ "   SUM(difference) AS todayenergy" + "   FROM Differences"
			+ "		GROUP BY" + " timestamp" + "	order by timestamp asc")
	List<DailyGenerationTodayEnergyDTO> getTotalEnergyValueForSpecificYield(@Param("siteid") int siteId,
			@Param("timestamp") Timestamp fdate, @Param("timeStamp") Timestamp tdate);

//(int siteId, Timestamp fDate, Timestamp tdate);

}
