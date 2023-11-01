package com.hummersoft.eira.service;

import java.util.List;

import com.hummersoft.eira.dto.SiteEnergyDtlDTO;
import com.hummersoft.eira.dto.SiteListDTO;
import com.hummersoft.eira.dto.SiteStatDTO;


public interface DashboardService {
	

	
	List<SiteStatDTO>  getSiteStatistics(Long userId);
	
	List<SiteEnergyDtlDTO> getSiteEnergyDtl(Long userId);
	
	List<SiteListDTO> getSiteList(Long userId);
	
	List<SiteListDTO> getSiteListforDashboard(Long userId);
	
	int getTotalEvent(Long userId);
	
	int getTodayEvent(Long userId);
	
	List<Object> getTopAlarmCount(Long userId);


}
