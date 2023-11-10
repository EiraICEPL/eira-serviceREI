package com.hummersoft.eira.service;

import java.util.List;
import java.util.Map;

public interface EquipmentEnergyMeterService {


	List<Map<String, Object>> findEquipmentBySiteId(Integer siteId);

	List<Map<String, Object>> findWeathrstionBySite(Integer siteId);

}
