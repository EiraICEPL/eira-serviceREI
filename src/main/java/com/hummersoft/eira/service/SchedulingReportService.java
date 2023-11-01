package com.hummersoft.eira.service;

import java.util.List;

import com.hummersoft.eira.model.UserReportMap;


public interface SchedulingReportService {
    List<UserReportMap> getReportByTimePeriod(String timeperiod);


}