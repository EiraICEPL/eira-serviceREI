package com.hummersoft.eira.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.dto.EquipmentViewDTO;
import com.hummersoft.eira.service.EquipmentService;
import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.EquipmentCategory;
import com.hummersoft.eira.model.EquipmentType;

@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipmentViewController {
	
	@Autowired
	private EquipmentService equipService;
	
	
	@GetMapping("/getEquipmentDtl={equipmentId}")
	public EquipmentViewDTO getEquipDetail(@PathVariable int equipmentId) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		EquipmentViewDTO equipmentDTO = new EquipmentViewDTO();
		
		Equipment equipment = equipService.getEquipmentbyId(equipmentId);
		
		if(null !=equipment) {
			
			Optional<EquipmentType> equipmentType = equipService.getEquipmentTypebyId(equipment.getEquipmentTypeID());
			
			if(null != equipmentType) {
				Optional<EquipmentCategory> equipCategory = equipService.getEquipmentCategoryByType(equipmentType.get().getCategoryID());
				
				equipmentDTO.setEquipmentCategory(equipCategory.get().getEquipmentCategory());
				equipmentDTO.setEquipmentCode(equipment.getEquipmentCode());
				equipmentDTO.setCapacity(equipment.getCapacity().toString());
				equipmentDTO.setCustomerReference(equipment.getCustomerReference());
				equipmentDTO.setCustomerNaming(equipment.getCustomerNaming());
				equipmentDTO.setEquipmentType(equipmentType.get().getEquipmentType());
				equipmentDTO.setComponents(equipment.getComponents());
				equipmentDTO.setDisconnectRating(equipment.getDisconnectRating());
				equipmentDTO.setDisconnectType(equipment.getDisconnectType());
				
				
				//Equipment History/Replacement Details
				equipmentDTO.setIsPrimary(equipment.getEquipmentSelection());
				equipmentDTO.setInstallationDate(sdf.format(equipment.getInstallationDate()));
				equipmentDTO.setWarantryExpire(sdf.format(equipment.getWarrantyDate()));
				equipmentDTO.setDescription(equipment.getDescription());
				equipmentDTO.setRemarks(equipment.getRemarks());
				
				List<EventDTO> eventDetails = equipService.findEventsByEquipment(equipment.getEquipmentId());
						
				equipmentDTO.setEventList(eventDetails);
				
				
			}
		}
		
		return equipmentDTO;
		
	}
	
	
	@GetMapping("/getEquipmentBySite/{siteId}")
	public ResponseEntity<List<Map<String, Object>>> getEquipmentBySiteId(@PathVariable BigInteger siteId) {
		List<Map<String, Object>> equipmentData = equipService.findEquipmentBySiteId(siteId);
		return ResponseEntity.ok(equipmentData);
	}

}
