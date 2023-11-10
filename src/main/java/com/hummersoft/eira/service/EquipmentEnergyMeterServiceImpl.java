package com.hummersoft.eira.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.repository.EquipmentEnergyMeterRepository;

import jakarta.transaction.Transactional;

@Service
public class EquipmentEnergyMeterServiceImpl implements EquipmentEnergyMeterService {
	@Autowired
	private EquipmentEnergyMeterRepository equipmentEnergyMeterRepository;

	@Transactional
	@Override
	public List<Map<String, Object>> findEquipmentBySiteId(Integer siteId) {
		return equipmentEnergyMeterRepository.findEquipmentBySiteId(siteId);
	}

	@Transactional
	@Override
	public List<Map<String, Object>> findWeathrstionBySite(Integer siteId) {
		return equipmentEnergyMeterRepository.findWeathrstionBySite(siteId);

	}
}
