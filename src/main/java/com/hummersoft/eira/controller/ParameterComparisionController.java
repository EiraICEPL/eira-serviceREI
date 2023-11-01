package com.hummersoft.eira.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hummersoft.eira.service.ParameterComparisionService;

@RestController
@RequestMapping("/eira/ParameterComparision")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParameterComparisionController {

	@Autowired
	private ParameterComparisionService parameterComparisionService;

	@PostMapping("/comValue")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<Object> getDailyGenValue(@RequestBody Object dalilygen) {
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
			List<Map<String, String>> parameterList = new ArrayList<Map<String, String>>();
			JSONArray equipmentIds = jsonobj.getJSONArray("EquipmentIds");
			String strEquipmentids = equipmentIds.toString();
			List<Integer> listEquipmentIds = objectMapper.readValue(strEquipmentids, List.class);
			HashMap<String, String> mapValue = null;
			JSONArray parameters = jsonobj.getJSONArray("Parameters");
			List<Object[]> arrayOfArrays = parameterComparisionService.getCompValue(jsonobj.getInt("SiteId"),
					jsonobj.getString("Range"), fDate, tdate, listEquipmentIds, parameters);

			String strParameters = parameters.toString();
			List<String> listParameters;

			listParameters = objectMapper.readValue(strParameters, List.class);

			listParameters.add("equipmentid");
			listParameters.add("timestamp");

			for (Object[] innerArray : arrayOfArrays) {
				int minLength = Math.min(innerArray.length, listParameters.size());
				mapValue = new HashMap<String, String>();
				for (int i = 0; i < minLength; i++) {
					Object element = innerArray[i];
					String key = listParameters.get(i);
					if (element != null) {
						mapValue.put(key, element.toString());
					}
				}
				parameterList.add(mapValue);
			}
			return new ResponseEntity<>(parameterList, HttpStatus.OK);

		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
