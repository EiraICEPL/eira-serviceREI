package com.hummersoft.eira.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.dto.DashboardSiteList;
import com.hummersoft.eira.dto.EnergyDetailsDTO;
import com.hummersoft.eira.dto.EventStatisticsDTO;
import com.hummersoft.eira.dto.SiteEnergyDtlDTO;
import com.hummersoft.eira.dto.SiteListDTO;
import com.hummersoft.eira.dto.SiteStatDTO;
import com.hummersoft.eira.dto.siteEnergyStatDTO;
import com.hummersoft.eira.service.DashboardService;


@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardController {
	

	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private EnergyDetailsDTO energyDetails;
	
	@GetMapping("/getSiteStatistics={userId}")
	public List<SiteStatDTO> getSiteStatistics(@PathVariable Long userId) {
			
		 List<SiteStatDTO> siteStatt = dashboardService.getSiteStatistics(userId);		 		
		 return siteStatt;
	}
	
	@GetMapping("/loadSiteList={userId}")
	public ResponseEntity<Object> getSiteListDetails(@PathVariable Long userId) {
		
		try {
			
			List<SiteListDTO> siteList = dashboardService.getSiteList(userId);
			List<DashboardSiteList> siteBean = new ArrayList<>();
					
			return new ResponseEntity<>(siteList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/loadDashboardDetails={userId}")
	public ResponseEntity<Object> getDashboardDetails(@PathVariable Long userId) {
		
		Double varDblTotalEnergy = 0.0;
		Double varDblTodayEnergy = 0.0;
		Double varDblYestTotalEnergy = 0.0;
		String varStrTotalEnergyUOM = null;
		String varStrTodayEnergyUOM;
		
		try {
			
			Map<String, Object> data = new HashMap<>();
			List<siteEnergyStatDTO> SiteEnergyList = new ArrayList<>();
			 //List siteStatt = new ArrayList<>();
			//List<SiteStatDTO> siteStatt = dashboardService.getSiteStatistics(userId);
			List<SiteStatDTO> siteStatt = new ArrayList<>();
			SiteStatDTO roofsite = new SiteStatDTO();
			SiteStatDTO utilSite = new SiteStatDTO();
			List<SiteListDTO> siteList = dashboardService.getSiteListforDashboard(userId);
			//System.out.println(siteList.size());
			for (int i=0; i<siteList.size(); i++) {
				if (siteList.get(i).getSitetype().equals("Rooftop")) {
					roofsite.setSiteType("RoofTopSites");
					roofsite.setRoofTopSites(roofsite.getRoofTopSites()+1);
					if (siteList.get(i).getStatus().equals("Active")) 
						roofsite.setActiveStatus(roofsite.getActiveStatus()+1);
					else if (siteList.get(i).getStatus().equals("Warning"))
							roofsite.setWarningStatus(roofsite.getWarningStatus()+1);
					else if (siteList.get(i).getStatus().equals("Down"))
						roofsite.setDownStatus(roofsite.getDownStatus()+1);
					else if(siteList.get(i).getStatus().equals("Offline")) 
						roofsite.setOfflineStatus(roofsite.getOfflineStatus()+1);
						
				}else if (siteList.get(i).getSitetype().equals("Utility")) {
					utilSite.setSiteType("utilitySites");
					utilSite.setUtilitySites(utilSite.getUtilitySites()+1);
					if (siteList.get(i).getStatus().equals("Active")) 
						utilSite.setActiveStatus(utilSite.getActiveStatus()+1);
					else if (siteList.get(i).getStatus().equals("Warning"))
						utilSite.setWarningStatus(utilSite.getWarningStatus()+1);
					else if (siteList.get(i).getStatus().equals("Down"))
						utilSite.setDownStatus(utilSite.getDownStatus()+1);
					else if(siteList.get(i).getStatus().equals("Offline")) 
						utilSite.setOfflineStatus(utilSite.getOfflineStatus()+1);
				}
				if (i<5) {
					siteEnergyStatDTO statDTO = new siteEnergyStatDTO();
					statDTO.setTodayEnergy(siteList.get(i).getTodayEnergy().toString());
					statDTO.setSiteId(siteList.get(i).getSiteId().toString());
					statDTO.setSiteName(siteList.get(i).getSiteName());
					
					SiteEnergyList.add(statDTO);
				}
			}
			siteStatt.add(roofsite);
			siteStatt.add(utilSite);
			data.put("SiteStatistics", siteStatt);
			
			data.put("SiteEnergyStatistics", SiteEnergyList);
			
			//events details
			int lstAllEvents = dashboardService.getTotalEvent(userId);
			int lstTodayEvent = dashboardService.getTodayEvent(userId);
			energyDetails.setTodayEvents(lstTodayEvent);
			energyDetails.setTotalEvents(lstAllEvents);
			
			
			List<EventStatisticsDTO> eventStatList = new ArrayList<>();
			List<Object> eventStatistics = dashboardService.getTopAlarmCount(userId);
			
			for (Object object : eventStatistics) {
				Object[] obj = (Object[]) object;
				
				EventStatisticsDTO eventStat = new EventStatisticsDTO();
				eventStat.setAlarmCount(obj[0].toString());
				eventStat.setSiteId(obj[1].toString());
				eventStat.setSiteName(obj[2].toString());
				
				eventStatList.add(eventStat);
					
			}
			
			data.put("EventStatistcs", eventStatList);
			
			List<SiteEnergyDtlDTO> siteEnergyDtl = dashboardService.getSiteEnergyDtl(userId);
			varDblTotalEnergy = Double.parseDouble(siteEnergyDtl.get(0).getTotalEnergy());
			varDblTodayEnergy = Double.parseDouble(siteEnergyDtl.get(0).getTodayEnergy());
			//data.put("SiteEnergyDetails", siteEnergyDtl);
			
			energyDetails.setLastChecked(siteEnergyDtl.get(0).getLastChecked());
			energyDetails.setLastDataReceived(siteEnergyDtl.get(0).getLastDataReceived());
			energyDetails.setProductionOn(siteEnergyDtl.get(0).getProductionOn());
			
			Double co2avoided = 0.00067 * varDblTodayEnergy ;
			energyDetails.setTodayCo2Avoided(String.format("%.2f", co2avoided) + " T");
			
			//Double yestco2aavoided = 0.00067 * varDblYestTotalEnergy;
			//energyDetails.setCo2(String.format("%.2f", yestco2aavoided) + " T");
			
			if (varDblTodayEnergy >= 1000) {
				varDblTodayEnergy = varDblTodayEnergy / 1000;
				varStrTodayEnergyUOM = " MWh";
				energyDetails.setLoadFactor(String.format("%.2f", ((varDblTodayEnergy / (11.019 * 24)) * 100)) + " %");
				
			} else if (varDblTodayEnergy < 1) {
				varDblTodayEnergy = varDblTodayEnergy * 1000;
				varStrTodayEnergyUOM = " Wh";
				energyDetails.setLoadFactor(String.format("%.2f", ((varDblTodayEnergy / (11.019 * 24)) * 100)) + " %");
				
			}
			else {
				varStrTodayEnergyUOM = " kWh";
				energyDetails.setLoadFactor(String.format("%.2f", ((varDblTodayEnergy / (11.019 * 24)) * 100)) + " %");
			}
			
			if (varDblTotalEnergy >= 1000000) {
				varDblTotalEnergy = varDblTotalEnergy / 1000000;
				varStrTotalEnergyUOM = " GWh";

				
				
			} else if (varDblTotalEnergy >= 1000) {
				varDblTotalEnergy = varDblTotalEnergy / 1000;
				varStrTotalEnergyUOM = " MWh";
				

			} else if (varDblTotalEnergy < 1) {
				varDblTotalEnergy = varDblTotalEnergy * 1000;
				varStrTotalEnergyUOM = " Wh";


			}
			
			energyDetails.setTodayEnergy(String.format("%.2f", varDblTodayEnergy) + varStrTodayEnergyUOM);
			energyDetails.setTotalEnergy(String.format("%.2f", varDblTotalEnergy) + varStrTotalEnergyUOM);
			energyDetails.setLastDownTime(siteEnergyDtl.get(0).getLastDownTime());
			//energyDetails.setLastDownTime(siteEnergyDtl.get(0).getLastDownTime());			
			
			data.put("SiteEnergyDetails", energyDetails); 
			return new ResponseEntity<>(data, HttpStatus.OK);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

}

