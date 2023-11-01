package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONArray;

public interface ParameterComparisionService {

	List<Object[]> getCompValue(int siteid, String range, Timestamp fDate, Timestamp tdate, List<Integer> listEquipmentIds, JSONArray parameters);

}
