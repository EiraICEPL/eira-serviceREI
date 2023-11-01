package com.hummersoft.eira.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.common.SiteViewUtility;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.dto.SiteViewDTO;
import com.hummersoft.eira.model.DataSource;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.Site;
import com.hummersoft.eira.service.SiteService;

@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SiteViewController {
	
	@Autowired
	private SiteService siteService;
	
	
	@Autowired
	private SiteViewUtility siteUtil;
	
	SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("/getSiteDetails={siteId}")
	public SiteViewDTO getSiteDetails(@PathVariable int siteId) {
		
		Double Sum = 0.0;
		//Double annualyield = 0.0;
		
		Double dblYesterdayTotalEnergy = 0.0;
		Double dblTotalEnergy = 0.0;
		Double dblTodayEnergy = 0.0;
		Double dblTodayHoursOn = 0.0;
		Double dblCVTodayHoursOn = 0.0;
		Date LstUpdatedDate = null;
		
		//Map<String,List> equipmentMap = new HashMap<>();
		List<EquipmentDTO> lstEquipmentinv = new ArrayList<>();
		List<EquipmentDTO> lstEquipmentEnergyMeter = new ArrayList<>();
		List<EquipmentDTO> lstEquipmentScbs = new ArrayList<>();
		List<EquipmentDTO> lstEquipmentTrackers = new ArrayList<>();
		
		SiteViewDTO siteDTO = new SiteViewDTO();
		Optional<Site> site = siteService.getSiteById(siteId);
		
		siteDTO.setSiteCode(site.get().getSiteCode());
		siteDTO.setSiteID(siteId);
		
		//List<Equipment> lstequipment = siteService.listInvertersBySiteId(siteId);
		List<Equipment> lstequipmentCount = siteService.listEquipmentComunicationCount(siteId);
		
		List<Integer> inv_id = new ArrayList<>();
		
		//get all equipments
		List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(siteId);
		for(int i=0; i<lstAllEquipments.size(); i++) {
			
			if (lstAllEquipments.get(i).getCategory().equals("CENTRLINVRTR") || lstAllEquipments.get(i).getCategory().equals("STRINGINVRTR")) {
				lstEquipmentinv.add(lstAllEquipments.get(i));
				inv_id.add(lstAllEquipments.get(i).getEquipmentId());
			}
			
			if (lstAllEquipments.get(i).getCategory().equals("STRINGCOMBINER"))
				lstEquipmentScbs.add(lstAllEquipments.get(i));
			
			if (lstAllEquipments.get(i).getCategory().equals("Primary Energy Meter") || lstAllEquipments.get(i).getCategory().equals("Secondary Energy Meter"))
				lstEquipmentEnergyMeter.add(lstAllEquipments.get(i));
			
			if (lstAllEquipments.get(i).getCategory().equals("Tracker Sensor"))
				lstEquipmentTrackers.add(lstAllEquipments.get(i));		
		}
		
		List<DataSource> lstDataSource =siteService.getEnergyDtlsForInvertersBySiteId(siteId, inv_id);
				
		siteDTO.setEquipmentList(siteUtil.inverterEquipmentList(siteId,site.get().getTodayEnergyFlag(), lstDataSource, lstEquipmentinv));
		
		
		for (int i = 0; i < lstEquipmentinv.size(); i++) {
			Double capacity = lstEquipmentinv.get(i).getCapacity();

			Sum = Sum + capacity;

		}
		
		
		siteDTO.setInvertersCapacity(String.format("%.2f", Sum) + " kW");
		siteDTO.setEquipmentsCapacity(String.format("%.2f", Sum) + " kW");
		siteDTO.setEquipmentCount(lstequipmentCount.size());
		siteDTO.setInverterCount(lstEquipmentinv.size());
		siteDTO.setEnergymeterCount(lstEquipmentEnergyMeter.size());
		siteDTO.setInstallationCapacity(site.get().getInstallationCapacity());
		siteDTO.setSiteName(site.get().getSiteName());
		siteDTO.setSiteCode(site.get().getSiteCode());
		
		//if (site.get().getInstallationCapacity() >= 1000) {
			//siteDTO.setCapacity(String.format("%.2f", site.get().getInstallationCapacity() / 1000) + " MW");
		//} else {
			siteDTO.setCapacity(String.format("%.2f", site.get().getInstallationCapacity()) + " kWp");
		//}
		
		
		
		Integer InverterCount = lstDataSource.stream().filter(p -> p.getDismandalFlag() == 0)
				.collect(Collectors.toList()).size();

		for (int j = 0; j < InverterCount; j++) {
			
			
			if (lstDataSource.get(j).getTotalEnergy() != null) {
				dblTotalEnergy += lstDataSource.get(j).getTotalEnergy();
			}
			if (lstDataSource.get(j).getTodayEnergy() != null) {
				dblTodayEnergy += lstDataSource.get(j).getTodayEnergy();
				//System.out.println(" raw date today energy "+dblTodayEnergy);
			}
			if (lstDataSource.get(j).getYesterdayTotalEnergy() != null) {
				dblYesterdayTotalEnergy += lstDataSource.get(j).getYesterdayTotalEnergy();
				//System.out.println(" dblYesterdayTotalEnergy "+dblYesterdayTotalEnergy);
			}

			if (lstDataSource.get(j).getTodayHoursOn() != null) {
				dblTodayHoursOn = lstDataSource.get(j).getTodayHoursOn();

				if (dblCVTodayHoursOn < dblTodayHoursOn) {
					dblCVTodayHoursOn = dblTodayHoursOn;
				}

			}
			
			if (lstDataSource.get(j).getLastDataReceived() != null) {
				//logger.debug("LastUpdatedDataOnTime : "+lstDataSource.get(j).getLastDataReceived());
				if (LstUpdatedDate == null) {
					LstUpdatedDate = lstDataSource.get(j).getLastDataReceived();

				} else if (LstUpdatedDate.compareTo(lstDataSource.get(j).getLastDataReceived()) < 0) {
					LstUpdatedDate = lstDataSource.get(j).getLastDataReceived();
				}

			}
		}
		
		
		if(site.get().getTodayEnergyFlag()== 0 &&  dblTotalEnergy != 0.00) {
			//System.out.println("dblTotalEnergy  "+dblTotalEnergy);
			//System.out.println(dblTotalEnergy - dblYesterdayTotalEnergy);
			dblTodayEnergy = dblTotalEnergy - dblYesterdayTotalEnergy;
			//System.out.println(" fdsfsv dblTodayEnergy "+dblTodayEnergy);
		}
		dblTotalEnergy = dblTotalEnergy / 1000;
		dblYesterdayTotalEnergy = dblYesterdayTotalEnergy / 1000;
		
		/*
		 * if (dblTodayEnergy >= 1000) { //System.out.println("In if loop =1000");
		 * //System.out.println(" dblTodayEnergy / 1000 "+dblTodayEnergy / 1000); Double
		 * dblTemp0 = dblTodayEnergy / 1000;
		 * //System.out.println("dblTemp0  "+dblTemp0);
		 * siteDTO.setTodayEnergy(String.format("%.2f", dblTemp0) + " MWh");
		 * 
		 * Double co2avioded = 0.00067 * dblTemp0 * 1000;
		 * siteDTO.setCo(String.format("%.2f", co2avioded) + " T");
		 * 
		 * } else {
		 */
			//System.out.println("In if loop less than 1000");
			siteDTO.setTodayEnergy(String.format("%.2f", dblTodayEnergy) + " kWh");
			Double co2avioded = 0.00067 * dblTodayEnergy;
			siteDTO.setCo(String.format("%.2f", co2avioded) + " T");

		//}

		siteDTO.setTotalEnergy(String.format("%.2f", dblTotalEnergy) + " MWh");
		Double Totalco2avioded = 0.00067 * dblTotalEnergy * 1000;
		siteDTO.setTotalCo2(String.format("%.2f", Totalco2avioded) + " T");

		//Double loadfactor = ((dblYesterdayTotalEnergy) / (site.get().getInstallationCapacity() * 24)) * 100;
		Double cuf = ((dblTodayEnergy) / (Sum * 24)) * 100;
		siteDTO.setCuf(String.format("%.2f", cuf) + " %");
		

		if (dblTodayEnergy >= 1000) {
			Double dblTemp = dblTodayEnergy / 1000;
			siteDTO.setTodayProductionYeild(String.format("%.2f", dblTemp) + " MWh");
			//siteDTO.setLoadFactor()
		} else {
			siteDTO.setTodayProductionYeild(String.format("%.2f", dblTodayEnergy) + " kWh");
		}
		
		siteDTO.setTotalProductionYeild(String.format("%.2f", dblTotalEnergy) + " MWh");
		siteDTO.setYesterdayProductionYeild(String.format("%.2f", dblYesterdayTotalEnergy) + " MWh");
		
		if(null != LstUpdatedDate)
			siteDTO.setProductionDate(sdfdate.format(LstUpdatedDate));
		
		
		/*
		 * List<AnnualYieldDTO> annualYield = siteService.getAnnualYieldForSite(siteId);
		 * 
		 * if(annualYield.size() >0) { annualyield =
		 * annualYield.get(0).getAnnualYieldValue();
		 * 
		 * if (annualyield >= 1000) { Double dblTemp0 = annualyield / 1000;
		 * siteDTO.setAnnualYield(String.format("%.2f", dblTemp0) + " MWh");
		 * 
		 * } else if (annualyield >= 100000) { Double dblTemp0 = annualyield / 1000000;
		 * siteDTO.setAnnualYield(String.format("%.2f", dblTemp0) + " GWh"); } else {
		 * siteDTO.setAnnualYield(String.format("%.2f", annualyield) + " kWh");
		 * 
		 * } }
		 */

		//events details
		List<EventDTO> lstAllEvents = siteService.findEventsBySite(siteId);
		int lstTodayEvent = siteService.findTodayEvent(siteId);
		
		if (!lstAllEvents.isEmpty())
			siteDTO.setEventList(lstAllEvents);
		
		Integer todayEvnt = lstTodayEvent;
		Integer totalEvnt = lstAllEvents.size();
		siteDTO.setTotalEvents(totalEvnt.toString());
		siteDTO.setTodayEvents(todayEvnt.toString());
		
		return siteDTO;
		
	}
	
}

