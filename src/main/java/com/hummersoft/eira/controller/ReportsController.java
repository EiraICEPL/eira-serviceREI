package com.hummersoft.eira.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.common.ReportGenratorPDF;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.service.SchedulingReportService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportsController {

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

}

