package com.hummersoft.eira.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.dto.SiteEnergyDtlDTO;
import com.hummersoft.eira.dto.SiteListDTO;
import com.hummersoft.eira.dto.SiteStatDTO;
import com.hummersoft.eira.model.EventDetail;
import com.hummersoft.eira.repository.EventRepository;
import com.hummersoft.eira.repository.SiteRepository;


@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	private SiteRepository siteRepo;
	
	@Autowired
	private EventRepository eventRepo;

	
	@Override
	public List<SiteStatDTO> getSiteStatistics(Long userId) {
		
		//List<SiteStatDTO> sitedyl=siteRepo.getSiteStatistics(userId);
		//updated query
		return siteRepo.getSiteStatistics(userId);
	}

	@Override
	public List<SiteEnergyDtlDTO> getSiteEnergyDtl(Long userId) {
		
		return siteRepo.getSiteEnergyDetails(userId);
	}

	@Override
	public List<SiteListDTO> getSiteList(Long userId) {
		
		return siteRepo.getSiteList(userId);
	}

	@Override
	public int getTotalEvent(Long userId) {
		
		return eventRepo.findTotalEventsByUserId(userId);
	}

	@Override
	public int getTodayEvent(Long userId) {
		
		return eventRepo.findTodayEventsByUserId(userId);
	}

	@Override
	public List<Object> getTopAlarmCount(Long userId) {
		// TODO Auto-generated method stub
		return eventRepo.findTopFiveEventbyUserId(userId);
	}

	@Override
	public List<SiteListDTO> getSiteListforDashboard(Long userId) {
		// TODO Auto-generated method stub
		return siteRepo.getSiteListForDashboard(userId);
	}
	
}
