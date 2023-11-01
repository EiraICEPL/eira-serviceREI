package com.hummersoft.eira.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.repository.SchedulingReportRepository;

@Service
public class SchedulingReportServiceImpl implements SchedulingReportService {

    @Autowired
    private SchedulingReportRepository schedulingReportRepository;

   
    @Override
    public List<UserReportMap> getReportByTimePeriod(String timeperiod) {
        return schedulingReportRepository.findByTimePeriod(timeperiod);
    }


}
