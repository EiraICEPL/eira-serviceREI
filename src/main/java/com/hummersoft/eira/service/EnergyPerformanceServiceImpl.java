package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.repository.EnergyPerformanceRepository;
import com.hummersoft.eira.repository.GeneratinDataRepository;

@Service
public class EnergyPerformanceServiceImpl implements  EnergyPerformanceService {
	@Autowired
	private GeneratinDataRepository GeneratinDataRepo;
	
	@Autowired
	private EnergyPerformanceRepository energyPerformanceRepo;
	@Override
	public List<EnergyPerformanceDTO> getEnergyPerformanceValue(int siteId, String range, Timestamp fDate,
			Timestamp tdate, List<Integer> listEquipmentIds) {
		List<EnergyPerformanceDTO> dailyGenValue = null;
		int EnergyFlag = GeneratinDataRepo.getEnergyFlag(siteId);
		if ((range.toLowerCase()).equals("daily")) {
			if (EnergyFlag == 1) {
				System.out.println("");
				dailyGenValue = energyPerformanceRepo.getDgrTodayenergyValue(siteId, fDate, tdate,listEquipmentIds );
				
			} else if (EnergyFlag == 0) {
				dailyGenValue = energyPerformanceRepo.getDgrTotalEnergyValue(siteId, fDate, tdate, listEquipmentIds);
			}
		}else if ((range.toLowerCase()).equals("custom")) {
			if (EnergyFlag == 1) {
				System.out.println("");
				dailyGenValue = energyPerformanceRepo.getBarChartTodayenergyValue(siteId, fDate, tdate,listEquipmentIds );
				
			} else if (EnergyFlag == 0) {
				dailyGenValue = energyPerformanceRepo.getBarChartTotalEnergyValue(siteId, fDate, tdate, listEquipmentIds);
			}
		}
		return dailyGenValue;
	}

}
