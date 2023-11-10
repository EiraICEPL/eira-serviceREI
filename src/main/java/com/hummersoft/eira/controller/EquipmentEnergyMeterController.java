package com.hummersoft.eira.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.service.EquipmentEnergyMeterService;

@RestController
@RequestMapping("/equipment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipmentEnergyMeterController {
    @Autowired
    private EquipmentEnergyMeterService equipmentEnergyMeterService;
	
	@GetMapping("/getEquipaBySite/{siteId}")
	public ResponseEntity<List<Map<String, Object>>> getEquipmentBySiteId(@PathVariable Integer siteId) {
		List<Map<String, Object>> equipmentData = equipmentEnergyMeterService.findEquipmentBySiteId(siteId);
		return ResponseEntity.ok(equipmentData);
	}
	
	@GetMapping("/getWeathrstionBySite/{siteId}")
	public ResponseEntity<List<Map<String, Object>>> getWeathrstionBySite(@PathVariable Integer siteId) {
		List<Map<String, Object>> weathrstion = equipmentEnergyMeterService.findWeathrstionBySite(siteId);
		return ResponseEntity.ok(weathrstion);
	}

}
