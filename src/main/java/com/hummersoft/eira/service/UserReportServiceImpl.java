package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.dto.ReportMasterDTO;
import com.hummersoft.eira.model.ReportMaster;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.repository.UserReportRepository;

import jakarta.transaction.Transactional;

@Service
public class UserReportServiceImpl implements UserReportService {

	@Autowired
	private UserReportRepository userReportRepository;

	@Transactional
	@Override
	public UserReportMap saveUserReportMap(UserReportMap userReport) {
		return userReportRepository.save(userReport);
	}

	@Transactional
	@Override
	public UserReportMap updateReport(UserReportMap existingReport) {
		return userReportRepository.saveAndFlush(existingReport);
	}

	@Transactional
	@Override
	public Optional<UserReportMap> findByreportMapId(BigInteger reportMapId) {
		return userReportRepository.findById(reportMapId);
	}

	@Transactional
	@Override
	public List<UserReportMap> findAll() {
		return userReportRepository.findAll();
	}
//	@Transactional
	@Override
	public List<UserReportMap> findByUserId(Integer userId) {
		System.out.println("userid in Service IMPL+"+userId);
		List<UserReportMap>  detailsbyuserid=userReportRepository.findByUserId(userId);
		System.out.println("jke"+detailsbyuserid);
		return detailsbyuserid;
		
	}

	@Override
	public List<UserReportMap> findAllActiveReport() {
		return userReportRepository.findAllActiveReport();
	}

	@Override
	public List<UserReportMap> findAllInActiveReport() {
		return userReportRepository.findAllInActiveReport();
	}

	@Transactional
	@Override
	public void deactivatereportMapId(BigInteger reportMapId) {
		userReportRepository.deactivatereportMapId(reportMapId);
	}

	@Transactional
	@Override
	public void activatereportMapId(BigInteger reportMapId) {
		userReportRepository.activatereportMapId(reportMapId);

	}

	@Transactional
	@Override
	public void deletereportMapById(BigInteger reportMapId) {
		userReportRepository.deleteById(reportMapId);

	}

	@Override
	@Transactional
	public List<ReportMasterDTO> findAllReportNames() {
        return userReportRepository.findAllReportName();
       
    }
}
