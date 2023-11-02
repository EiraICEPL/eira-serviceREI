package com.hummersoft.eira.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.dto.ReportMasterDTO;
import com.hummersoft.eira.model.ReportMaster;
import com.hummersoft.eira.model.UserReportMap;


@Repository
public interface UserReportRepository extends JpaRepository<UserReportMap, BigInteger> {

	@Query(value = "SELECT * FROM eampm.userreportmap WHERE userId = :userId AND activeflag = 1", nativeQuery = true)
	List<UserReportMap> findByUserId(@Param("userId") Integer userId);

	@Query(value = "SELECT * FROM eampm.userreportmap WHERE activeflag = 1", nativeQuery = true)
	List<UserReportMap> findAllActiveReport();

	@Query(value = "SELECT * FROM eampm.userreportmap WHERE activeflag = 0", nativeQuery = true)
	List<UserReportMap> findAllInActiveReport();

	@Modifying
	@Query(value = "UPDATE eampm.userreportmap SET activeflag =0 WHERE reportMapId = :reportMapId", nativeQuery = true)
	void deactivatereportMapId(BigInteger reportMapId);

	@Modifying
	@Query(value = "UPDATE eampm.userreportmap SET activeflag = 1 WHERE reportMapId = :reportMapId", nativeQuery = true)
	void activatereportMapId(BigInteger reportMapId);
	
  
    @Query(value="SELECT  r.reportName,r.reportId FROM eampm.reportmaster r", nativeQuery = true)
	List<ReportMasterDTO> findAllReportName();

}
