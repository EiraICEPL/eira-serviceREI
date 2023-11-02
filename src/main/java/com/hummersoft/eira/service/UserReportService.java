package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.hummersoft.eira.dto.ReportMasterDTO;
import com.hummersoft.eira.model.UserReportMap;


public interface UserReportService {
	UserReportMap saveUserReportMap(UserReportMap userReportMap);

	UserReportMap updateReport(UserReportMap existingReport);

	Optional<UserReportMap> findByreportMapId(BigInteger reportMapId);

	List<UserReportMap> findAll();

	List<UserReportMap> findByUserId(Integer userId);

	List<UserReportMap> findAllActiveReport();

	List<UserReportMap> findAllInActiveReport();

	void deactivatereportMapId(BigInteger reportMapId);

	void activatereportMapId(BigInteger reportMapId);

	void deletereportMapById(BigInteger reportMapId);

	List<ReportMasterDTO> findAllReportNames();


}
