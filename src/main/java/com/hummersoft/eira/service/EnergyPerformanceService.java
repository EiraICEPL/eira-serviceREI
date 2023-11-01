package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import com.hummersoft.eira.dto.EnergyPerformanceDTO;

public interface EnergyPerformanceService {

	List<EnergyPerformanceDTO> getEnergyPerformanceValue(int int1, String string, Timestamp fDate,
			Timestamp tdate, List<Integer> listEquipmentIds);

}
