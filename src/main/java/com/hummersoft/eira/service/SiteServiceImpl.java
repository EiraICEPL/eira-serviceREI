package com.hummersoft.eira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.dto.AnnualYieldDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.DataSource;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.Site;
import com.hummersoft.eira.repository.DataSourceRepository;
import com.hummersoft.eira.repository.EquipmentRepository;
import com.hummersoft.eira.repository.EventRepository;
import com.hummersoft.eira.repository.SiteRepository;

import jakarta.transaction.Transactional;

@Service
public class SiteServiceImpl implements SiteService{
	
	
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired
	private SiteRepository siteRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	
	@Autowired
	private DataSourceRepository datasourceRepo;
	
	@Transactional
	public List<Equipment> listInvertersBySiteId(int siteId) {
		return this.equipmentRepo.getInvEquipmenList(siteId);
	}

	@Override
	public List<Equipment> listEquipmentComunicationCount(int siteid) {
		return this.equipmentRepo.getEquipmenList(siteid);
	}

	
	@Override
	public Optional<Site> getSiteById(int siteid) {
		// TODO Auto-generated method stub
		return siteRepo.findById(siteid);
	}

	@Override
	public List<AnnualYieldDTO> getAnnualYieldForSite(int siteid) {
		// TODO Auto-generated method stub
		return siteRepo.getAnnualYieldForSite(siteid);
	}

	@Override
	public List<EquipmentDTO> listAllEquipment(int siteid) {
		// TODO Auto-generated method stub
		return this.equipmentRepo.getAllEquipmenList(siteid);
	}

	@Override
	public List<DataSource> getEnergyDtlsForInvertersBySiteId(int siteId, List<Integer> equipmentId) {
		// TODO Auto-generated method stub
		return this.datasourceRepo.getEnergyDetails(siteId, equipmentId);
	}

	@Override
	public List<EventDTO> findEventsBySite(int siteId) {
		// TODO Auto-generated method stub
		return eventRepo.findEventsBySiteId(siteId);
	}

	@Override
	public int findTodayEvent(int siteId) {
		// TODO Auto-generated method stub
		return eventRepo.findTodayEventsBySiteId(siteId);
	}

}

