package com.hummersoft.eira.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.service.UserReportService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/eira/reports")
public class UserReportController {

	@Autowired
	private UserReportService userreportService;

	@PostMapping("/addUserReportMap")
	public ResponseEntity<Object> addUserReportMap(@RequestBody UserReportMap userReport) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonString = objectMapper.writeValueAsString(userReport);
			JSONObject jsonobj = new JSONObject(jsonString);
			System.out.println("Received JSON: " + jsonobj);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String timestamp = now.format(formatter);
			Timestamp ts = Timestamp.valueOf(timestamp);

			UserReportMap addreport = new UserReportMap();
			addreport.setActiveFlag(jsonobj.getInt("activeFlag"));
			addreport.setCreationDate(ts);
			addreport.setMailId(jsonobj.getString("mailId"));
			addreport.setRange(jsonobj.getString("range"));
			addreport.setReportId(jsonobj.getInt("reportId"));
			addreport.setSiteId(jsonobj.getInt("siteId"));
			addreport.setTimePeriod(jsonobj.getString("timePeriod"));
			addreport.setUserId(jsonobj.getInt("userId"));

			UserReportMap savedUserReportMap = userreportService.saveUserReportMap(addreport);
			return ResponseEntity.ok(savedUserReportMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
		}
	}

	@PutMapping("/updateReport/{reportMapId}")
	public ResponseEntity<Object> updateReport(@PathVariable BigInteger reportMapId,
			@RequestBody Object updatedReport) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonString = objectMapper.writeValueAsString(updatedReport);
			JSONObject jsonobj = new JSONObject(jsonString);

			Optional<UserReportMap> existingOpt = userreportService.findByreportMapId(reportMapId);

			if (existingOpt.isPresent()) {
				UserReportMap existingReport = existingOpt.get();
				existingReport.setActiveFlag(jsonobj.getInt("activeFlag"));
				existingReport.setMailId(jsonobj.getString("mailId"));
				existingReport.setRange(jsonobj.getString("range"));
				existingReport.setReportId(jsonobj.getInt("reportId"));
				existingReport.setSiteId(jsonobj.getInt("siteId"));
				existingReport.setTimePeriod(jsonobj.getString("timePeriod"));
				existingReport.setUserId(jsonobj.getInt("userId"));
				UserReportMap updatedObj = userreportService.updateSector(existingReport);
				return ResponseEntity.ok(updatedObj);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/findByUserId/{userId}")
	public ResponseEntity<List<UserReportMap>> findByUserId(@PathVariable Integer userId) {
		System.out.println("Userid="+userId);
		List<UserReportMap> userReports = userreportService.findByUserId(userId);
		System.out.println("jhahdf");
		if (!userReports.isEmpty()) {
			return ResponseEntity.ok(userReports);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<UserReportMap>> findAll() {
		List<UserReportMap> report = userreportService.findAll();
		return ResponseEntity.ok(report);
	}

	@GetMapping("/findAllActiveReport")
	public List<UserReportMap> findAllActiveReport() {
		List<UserReportMap> activeReports = userreportService.findAllActiveReport();
		return activeReports;
	}

	@GetMapping("/findAllInActiveReport")
	public List<UserReportMap> findAllInActiveReport() {
		List<UserReportMap> Inactivereports = userreportService.findAllInActiveReport();
		return Inactivereports;
	}

	@PutMapping("/deactivate/{reportMapId}")
	public ResponseEntity<String> deactivatereportMapId(@PathVariable BigInteger reportMapId) {
		userreportService.deactivatereportMapId(reportMapId);
		return ResponseEntity.ok("Report deactivated successfully");
	}

	@PutMapping("/activate/{reportMapId}")
	public ResponseEntity<String> activatereportMapId(@PathVariable BigInteger reportMapId) {
		userreportService.activatereportMapId(reportMapId);
		return ResponseEntity.ok("Report activated successfully");
	}

	@DeleteMapping("/delete/{reportMapId}")
	public ResponseEntity<Object> deleteReport(@PathVariable BigInteger reportMapId) {
		userreportService.deletereportMapById(reportMapId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
