package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;

public interface DailyGenerationService {

	List<DailyGenerationTodayEnergyDTO> getDgrValue(int siteId, String range, Timestamp fDate, Timestamp tdate);

	List<DailyGenerationTodayEnergyDTO> getSpecificYieldGenerationValue(int siteId, String range, Timestamp fDate, Timestamp tdate);

}