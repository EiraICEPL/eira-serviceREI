package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.model.Equipment;
import com.hummersoft.eira.model.EquipmentCategory;
import com.hummersoft.eira.model.EquipmentType;
import com.hummersoft.eira.model.EventDetail;
import com.hummersoft.eira.repository.EquipmentCategoryRepository;
import com.hummersoft.eira.repository.EquipmentRepository;
import com.hummersoft.eira.repository.EquipmentTypeRepository;
import com.hummersoft.eira.repository.EventRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService{
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired
	private EquipmentTypeRepository equipTypeRepo;
	
	@Autowired
	private EquipmentCategoryRepository equipCategoryRepo;
	
	@Autowired
	private EventRepository eventRepo;

	@Override
	public Equipment getEquipmentbyId(int equipmentId) {
		// TODO Auto-generated method stub
		return equipmentRepo.getEquipmentbyequipid(equipmentId);
	}

	@Override
	public Optional<EquipmentType> getEquipmentTypebyId(int equipTypeId) {
		// TODO Auto-generated method stub
		return equipTypeRepo.findById(equipTypeId);
	}

	@Override
	public Optional<EquipmentCategory> getEquipmentCategoryByType(int categoryId) {
		// TODO Auto-generated method stub
		return equipCategoryRepo.findById(categoryId);
	}

	@Override
	public List<EventDTO> findEventsByEquipment(int equipmentId) {
		// TODO Auto-generated method stub
		return eventRepo.findEventsByEquipment(equipmentId);
	}
	
	@Transactional
	  @Override
	    public List<Map<String, Object>> findEquipmentBySiteId(BigInteger siteId) {
	        List<Object[]> equipmentData = equipmentRepo.findEquipmentBySiteId(siteId);
	        List<Map<String, Object>> result = new ArrayList<>();

	        for (Object[] row : equipmentData) {
	            Map<String, Object> equipmentMap = new HashMap<>();
	            equipmentMap.put("equipmentid", row[0]);
	            equipmentMap.put("customernaming", row[1]);
	            result.add(equipmentMap);
	        }

	        return result;
	}
	    

}

