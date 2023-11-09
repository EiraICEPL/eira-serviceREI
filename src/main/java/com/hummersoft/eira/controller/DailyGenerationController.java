package com.hummersoft.eira.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.EquipmentSpecificYieldDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;
import com.hummersoft.eira.service.DailyGenerationService;
import com.hummersoft.eira.service.EnergyPerformanceService;
import com.hummersoft.eira.service.SiteService;

@RestController
@RequestMapping("/eira/dailyEnergy")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DailyGenerationController {

	@Autowired
	private DailyGenerationService dailyGenerationService;

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private EnergyPerformanceService energyPerformanceService;

	@PostMapping("/genvalue")
	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<DailyGenerationTodayEnergyDTO> getDailyGenValue(@RequestBody Object dalilygen) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String jsonString = objectMapper.writeValueAsString(dalilygen);
			JSONObject jsonobj = new JSONObject(jsonString);
			String strFDate = jsonobj.getString("FromDate") + " 00:05:00";
			String strTDate = jsonobj.getString("ToDate") + " 13:30:00";
			Date fromDateP = dateFormat.parse(strFDate);
			Date toDateP = dateFormat.parse(strTDate);
			Timestamp fDate = new Timestamp(fromDateP.getTime());
			Timestamp tdate = new Timestamp(toDateP.getTime());
			List<DailyGenerationTodayEnergyDTO> genValue = dailyGenerationService.getDgrValue(jsonobj.getInt("SiteId"),
					jsonobj.getString("Range"), fDate, tdate);
			return genValue;

		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/specificYield")
	public List<SpecificYieldDTO> getDailySpecificYieldValue(@RequestBody Object specificyield) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String jsonString = objectMapper.writeValueAsString(specificyield);
			JSONObject jsonobj = new JSONObject(jsonString);
			String strFDate = jsonobj.getString("FromDate") + " 00:05:00";
			String strTDate = jsonobj.getString("ToDate") + " 13:30:00";
			Date fromDateP = dateFormat.parse(strFDate);
			Date toDateP = dateFormat.parse(strTDate);
			Timestamp fDate = new Timestamp(fromDateP.getTime());
			Timestamp tdate = new Timestamp(toDateP.getTime());
			List<DailyGenerationTodayEnergyDTO> genValue = dailyGenerationService.getSpecificYieldGenerationValue(
					jsonobj.getInt("SiteId"), jsonobj.getString("Range"), fDate, tdate);

			Double SumCapacity = 0.0;
			List<EquipmentDTO> lstEquipmentinv = new ArrayList<>();
			List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(jsonobj.getInt("SiteId"));
			for (int i = 0; i < lstAllEquipments.size(); i++) {
				if (lstAllEquipments.get(i).getCategory().equals("CENTRLINVRTR")
						|| lstAllEquipments.get(i).getCategory().equals("STRINGINVRTR")) {
					lstEquipmentinv.add(lstAllEquipments.get(i));
				}
			}
			for (int i = 0; i < lstEquipmentinv.size(); i++) {
				Double capacity = lstEquipmentinv.get(i).getCapacity();
				SumCapacity = SumCapacity + capacity;

			}
			List<SpecificYieldDTO> specificYieldInv = new ArrayList<>();
			 DecimalFormat df = new DecimalFormat("#.00");
		        
		       
			
			for (DailyGenerationTodayEnergyDTO generation : genValue) {
				SpecificYieldDTO specificDTO = new SpecificYieldDTO();
				if (generation.getTodayEnergy() != null && generation.getTodayEnergy() != 0) {
					Double SpecificYield = ((generation.getTodayEnergy()) / SumCapacity);

					specificDTO.setTodayEnergy(generation.getTodayEnergy());
					specificDTO.setTimeStamp(generation.getTimestamp());
					String formatted = df.format(SpecificYield);
					double SpecificYieldRounded = Double.parseDouble(formatted);
					specificDTO.setSpecificYield(SpecificYieldRounded);

				}
				specificYieldInv.add(specificDTO);
			}

			return specificYieldInv;

		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/equSpecificYield/daily")
	public List<EquipmentSpecificYieldDTO> getDailyEquSpecificYieldValue(@RequestBody Object specificyield) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			String jsonString = objectMapper.writeValueAsString(specificyield);
			JSONObject jsonobj = new JSONObject(jsonString);
			String strFDate = jsonobj.getString("FromDate") + " 00:05:00";
			String strTDate = jsonobj.getString("ToDate") + " 13:30:00";
			Date fromDateP = dateFormat.parse(strFDate);
			Date toDateP = dateFormat.parse(strTDate);
			Timestamp fDate = new Timestamp(fromDateP.getTime());
			Timestamp tdate = new Timestamp(toDateP.getTime());
//			List<DailyGenerationTodayEnergyDTO> genValue = dailyGenerationService.getSpecificYieldGenerationValue(
//					jsonobj.getInt("SiteId"), jsonobj.getString("Range"), fDate, tdate);
//			Optional<Site> site = siteService.getSiteById(jsonobj.getInt("SiteId"));
			List<EquipmentDTO> lstEquipmentinv = new ArrayList<>();
			List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(jsonobj.getInt("SiteId"));
//			List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(siteId);
			List<EquipmentSpecificYieldDTO> equSpeciYield=new ArrayList<EquipmentSpecificYieldDTO>();
//            EquSpecificYieldDTO siteDTO = new EquSpecificYieldDTO();
			
			for(int i=0; i<lstAllEquipments.size(); i++) {
				
				if (lstAllEquipments.get(i).getCategory().equals("CENTRLINVRTR") || lstAllEquipments.get(i).getCategory().equals("STRINGINVRTR")) {
					lstEquipmentinv.add(lstAllEquipments.get(i));
				}
			}
			List<Integer> listEquipmentIds = new ArrayList<Integer>();
			
			for(EquipmentDTO equipmentList : lstEquipmentinv ) {
				
				 listEquipmentIds.add(equipmentList.getEquipmentId());
		}
			List<EnergyPerformanceDTO> genValue = energyPerformanceService.getEnergyPerformanceValue(jsonobj.getInt("SiteId"),
					jsonobj.getString("Range"), fDate, tdate, listEquipmentIds );
			
			for(EnergyPerformanceDTO generationValue : genValue) {
				EquipmentSpecificYieldDTO specificDTO =new EquipmentSpecificYieldDTO();
//				SpecificYieldDTO specificDTO = new SpecificYieldDTO();
				for(EquipmentDTO equipmentList : lstEquipmentinv ) {
					if(generationValue.getEquipmentId()==equipmentList.getEquipmentId()) {
						if(!generationValue.getTodayEnergy().equals(null)) {
					Double equSpecificYield=(generationValue.getTodayEnergy()/equipmentList.getCapacity());
					specificDTO.setEquipmentid(generationValue.getEquipmentId());
					specificDTO.setTodayEnergy(generationValue.getTodayEnergy());
					specificDTO.setTimeStamp(generationValue.getTimestamp());
					String formatted = df.format(equSpecificYield);
					double SpecificYieldRounded = Double.parseDouble(formatted);
					specificDTO.setSpecificYield(SpecificYieldRounded);
					specificDTO.setEquipmentName(equipmentList.getCustomerNaming());
					equSpeciYield.add(specificDTO);
					}}	
			}
			}
			
		return equSpeciYield;

		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
