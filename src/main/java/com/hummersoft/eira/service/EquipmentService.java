package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.EquipmentCategory;
import com.hummersoft.eira.model.EquipmentType;

public interface EquipmentService {
	
	Equipment getEquipmentbyId(int equipId);
	
	Optional<EquipmentType> getEquipmentTypebyId(int equipTypeId);
	
	Optional<EquipmentCategory>  getEquipmentCategoryByType(int categoryId);
	
	List<EventDTO> findEventsByEquipment(int equipmentId);
	
	List<Map<String, Object>> findEquipmentBySiteId(BigInteger siteId);

	List<Object[]> findEquipmentStatistics(BigInteger siteId);
}
