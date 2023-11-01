package com.hummersoft.eira.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.service.EnergyPerformanceService;

@RestController
@RequestMapping("/eira/enrgyPerf")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnergyPerformanceController {
	@Autowired
	private EnergyPerformanceService energyPerformanceService;

	@PostMapping("/value")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<EnergyPerformanceDTO> getEnergyPerformanceValue(@RequestBody Object energyperformance) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String jsonString = objectMapper.writeValueAsString(energyperformance);
			JSONObject jsonobj = new JSONObject(jsonString);
			String strFDate = jsonobj.getString("FromDate") + " 00:05:00";
			String strTDate = jsonobj.getString("ToDate") + " 13:30:00";
			Date fromDateP = dateFormat.parse(strFDate);
			Date toDateP = dateFormat.parse(strTDate);
			Timestamp fDate = new Timestamp(fromDateP.getTime());
			Timestamp tdate = new Timestamp(toDateP.getTime());
			JSONArray equipmentIds=jsonobj.getJSONArray("EquipmentIds");
			//System.out.println("Equipmentid***************"+equipmentIds);
			String strEquipmentids = equipmentIds.toString();
			//System.out.println("String of equipmentid *************"+strEquipmentids);
			List<Integer> listEquipmentIds = objectMapper.readValue(strEquipmentids, List.class);
			//System.out.println("*******   listEquipmrntIds   ****"+listEquipmentIds);

			List<EnergyPerformanceDTO> genValue = energyPerformanceService.getEnergyPerformanceValue(jsonobj.getInt("SiteId"),
					jsonobj.getString("Range"), fDate, tdate, listEquipmentIds );
			return genValue;

		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
			return null; 
		}
	}
}
