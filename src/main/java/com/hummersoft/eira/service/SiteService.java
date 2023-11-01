package com.hummersoft.eira.service;

import java.util.List;
import java.util.Optional;

import com.hummersoft.eira.dto.AnnualYieldDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.DataSource;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.Site;

public interface SiteService {
	
	
	List<Equipment> listInvertersBySiteId(int siteId);
	
	List<Equipment> listEquipmentComunicationCount(int siteid);
	
	List<EquipmentDTO> listAllEquipment(int siteid);
	
	Optional<Site> getSiteById(int siteid);
	
	List<AnnualYieldDTO> getAnnualYieldForSite(int siteid);
	
	List<DataSource> getEnergyDtlsForInvertersBySiteId(int siteId, List<Integer> equipmentId);
	
	List<EventDTO> findEventsBySite(int equipmentId);
	
	int findTodayEvent(int siteId);

}
