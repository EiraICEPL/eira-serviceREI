package com.hummersoft.eira.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hummersoft.eira.Scheduler.PdfGenerator;
import com.hummersoft.eira.common.ReportGenratorPDF;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.service.SchedulingReportService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportsController {

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private ReportGenratorPDF reportGenerator;

	@Autowired
	private SchedulingReportService schedulingReportService;

	@GetMapping("/GenrateMonthlyReport")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		reportGenerator.generatePdfReport();
	}

	@GetMapping("/GetReportsByTimeperiod/{timeperiod}")
	public List<UserReportMap> getReportsByTimePeriod(@PathVariable String timeperiod) {
		return schedulingReportService.getReportByTimePeriod(timeperiod);
	}

	@GetMapping("/generateReport/siteid={siteid}&fromDate={fromdate}&todate={todate}")
	public ResponseEntity<byte[]> getGeneratedReport(@PathVariable Integer siteid, @PathVariable String fromdate,
			@PathVariable String todate) throws JSONException, Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String strFDate = fromdate + " 00:05:00";
		String strTDate = todate + " 13:30:00";
		Date fromDateP = dateFormat.parse(strFDate);
		Date toDateP = dateFormat.parse(strTDate);
		Timestamp fDate = new Timestamp(fromDateP.getTime());
		Timestamp tDate = new Timestamp(toDateP.getTime());

		ByteArrayOutputStream pdfOutputStream = pdfGenerator.generatePdfReport(siteid, fDate, tDate);
		 // Convert the ByteArrayOutputStream to a byte array
        byte[] pdfBytes = pdfOutputStream.toByteArray();

        // Create headers to specify the response as an attachment
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentLength(pdfBytes.length);
        headers.add("Content-Disposition", "attachment; filename=Asset Management Report.pdf");

        // Return the PDF as a ResponseEntity with the appropriate headers
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

	}

}
