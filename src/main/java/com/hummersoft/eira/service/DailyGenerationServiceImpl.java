package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;
import com.hummersoft.eira.repository.GeneratinDataRepository;

@Service
public class DailyGenerationServiceImpl implements DailyGenerationService {

	@Autowired
	private GeneratinDataRepository GeneratinDataRepo;
//	private DailyGenerationValueRepository dailyGenerationValueRepository;

	@Override
	public List<DailyGenerationTodayEnergyDTO> getDgrValue(int siteId, String range, Timestamp fDate, Timestamp tdate) {
		List<DailyGenerationTodayEnergyDTO> dailyGenValue = null;

		int EnergyFlag = GeneratinDataRepo.getEnergyFlag(siteId);
		System.out.println(EnergyFlag);
		if ((range.toLowerCase()).equals("daily")) {

			if (EnergyFlag == 1) {
				System.out.println("today");
				dailyGenValue = GeneratinDataRepo.getDgrTodayenergyValue(siteId, fDate, tdate);
			} else if (EnergyFlag == 0) {
				dailyGenValue = GeneratinDataRepo.getDgrTotalEnergyValue(siteId, fDate, tdate);
			}
			
		} else if ((range.toLowerCase()).equals("custom")) {
			System.out.println("1");
			if (EnergyFlag == 1) {
				dailyGenValue = GeneratinDataRepo.getDgrBarChartTodayEnergyValue(siteId, fDate, tdate);
			} else if (EnergyFlag == 0) {
				System.out.println("2");
				dailyGenValue = GeneratinDataRepo.getDgrBarChartTotalEnergyValue(siteId, fDate, tdate);
			}
		}
		return dailyGenValue;

	}

	@Override
	public List<DailyGenerationTodayEnergyDTO> getSpecificYieldGenerationValue(int siteId, String range, Timestamp fDate,
			Timestamp tdate) {
		
		List<DailyGenerationTodayEnergyDTO> dailyGenValue = null;

		int EnergyFlag = GeneratinDataRepo.getEnergyFlag(siteId);
		System.out.println(EnergyFlag);
		if ((range.toLowerCase()).equals("daily")) {

			if (EnergyFlag == 1) {
				System.out.println("today");
				dailyGenValue = GeneratinDataRepo.getTodatEnergyValueForSpecificYield(siteId, fDate, tdate);
			} else if (EnergyFlag == 0) {
				dailyGenValue = GeneratinDataRepo.getTotalEnergyValueForSpecificYield(siteId, fDate, tdate);
			}
			
		} else if ((range.toLowerCase()).equals("custom")) {
			System.out.println("1");
			if (EnergyFlag == 1) {
				dailyGenValue = GeneratinDataRepo.getDgrBarChartTodayEnergyValue(siteId, fDate, tdate);
			} else if (EnergyFlag == 0) {
				System.out.println("2");
				dailyGenValue = GeneratinDataRepo.getDgrBarChartTotalEnergyValue(siteId, fDate, tdate);
			}
		}
		return dailyGenValue;
		
	}

}
