package com.hummersoft.eira.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.dto.UserWidgetsDTO;
import com.hummersoft.eira.model.Widgets;
import com.hummersoft.eira.service.DailyGenerationService;
import com.hummersoft.eira.service.EnergyPerformanceService;
import com.hummersoft.eira.service.EquipmentService;
import com.hummersoft.eira.service.ParameterComparisionService;
import com.hummersoft.eira.service.SitemapService;
import com.hummersoft.eira.service.WidgetServices;

@RestController
@RequestMapping("/eira/widgets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WidgetController {

	@Autowired
	private WidgetServices widgetService;
	
	@Autowired
	private SitemapService sitemapService;
	
	@Autowired
	private EquipmentService equipService;
	
	@Autowired
	private UserWidgetsDTO userWidget;

	@Autowired
	private DailyGenerationService dailyGenerationService;

	@Autowired
	private EnergyPerformanceService energyPerformanceService;

	@Autowired
	private ParameterComparisionService parameterComparisionService;

	@PostMapping("/save")
	public ResponseEntity<Object> createWidget(@RequestBody Map<String, Object> requestMap) {
		try {
			JSONObject jsonobj = new JSONObject(requestMap);

			Widgets widget = new Widgets();
			widget.setChartType(jsonobj.getString("GraphType"));
			widget.setSiteId(jsonobj.getInt("SiteId"));
			widget.setUserId(jsonobj.getBigInteger("UserId"));
			widget.setRange(jsonobj.getString("Range"));
			widget.setWidgetName(jsonobj.getString("WidgetName"));
			widget.setTimePeriod(jsonobj.getString("TimePeriod"));
			widget.setActiveFlag(jsonobj.getInt("ActiveFlag"));
			JSONArray equipmentIdsArray = jsonobj.getJSONArray("EquipmentIds");
			Integer[] equipmentIds = new Integer[equipmentIdsArray.length()];
			for (int i = 0; i < equipmentIdsArray.length(); i++) {
				equipmentIds[i] = equipmentIdsArray.getInt(i);
			}
			widget.setEquipment_Ids(equipmentIds);

			JSONArray measureParameterArray = jsonobj.getJSONArray("Parameters");
			String[] measureParameter = new String[measureParameterArray.length()];
			for (int i = 0; i < measureParameterArray.length(); i++) {
				measureParameter[i] = measureParameterArray.getString(i);
			}
			widget.setMeasureParameter(measureParameter);

			Widgets createdWidget = widgetService.saveWidget(widget);

			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/getWidgetBySiteId")
	public ResponseEntity<UserWidgetsDTO> getWidgetsBySiteId(@RequestBody Object param){
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;
		try {
			
			jsonString = objectMapper.writeValueAsString(param);
			JSONObject jsonobj = new JSONObject(jsonString);
			BigInteger userId = jsonobj.getBigInteger("userId");
			BigInteger siteId = null;
			//if (null != jsonobj.getBigInteger("siteId"))
					siteId = jsonobj.getBigInteger("siteId");
			
			 List<Map<String, Object>> siteInfo = sitemapService.getSiteIdAndSiteNameByUserId(userId.intValue());
			
			List<Map<String, Object>> equipmentList = null;
			List<Widgets> widgets = null;
			List<DailyGenerationTodayEnergyDTO> dailyGenValue = null;
			List<EnergyPerformanceDTO> energyGenValue = null;
			
			
			//System.out.println("dfsfds "+siteId);
			
			if (null!= siteId && !siteId.equals(BigInteger.valueOf(0))) {
				
				widgets = widgetService.getWidgetsBySiteId(userId, siteId);
			}else {
				widgets = widgetService.getWidgetsByUserId(userId);
				if (! widgets.isEmpty())
					siteId=BigInteger.valueOf(widgets.get(0).getSiteId());
				
				
				widgets = widgetService.getWidgetsBySiteId(userId, siteId);
			}
			equipmentList = equipService.findEquipmentBySiteId(siteId);
			
			userWidget.setUserId(userId);
			userWidget.setEquipmentList(equipmentList);
			userWidget.setSiteList(siteInfo);
			userWidget.setWidgetList(new ArrayList<Widgets>());
			
			for (Widgets widget : widgets) {
				
				Date[] dateRange = setWidgetDateRange(widget.getTimePeriod());
				
				Timestamp[] timestamps = generateTimestamps(dateRange[0], dateRange[1]);
				widget.setFromDate(timestamps[0]);
				widget.setToDate(timestamps[1]);
				if (widget.getChartType().equals("Daily Generation")) {
					dailyGenValue = dailyGenerationService.getDgrValue(widget.getSiteId(), widget.getRange(),
							timestamps[0], timestamps[1]);
					widget.setDailygenerationChartData(dailyGenValue);
				} else if (widget.getChartType().equals("Energy Performance")) {
					Integer[] equipmentIds = widget.getEquipment_Ids();
					List<Integer> intList = new ArrayList<>();
					for (int intValue : equipmentIds) {
						intList.add(intValue);
					}
					energyGenValue = energyPerformanceService.getEnergyPerformanceValue(widget.getSiteId(),
							widget.getRange(), timestamps[0], timestamps[1], intList);
					widget.setEnergyPerformanceChartData(energyGenValue);
				} else if (widget.getChartType().equals("Parameter Comparision")) {
					List<Map<String, String>> parameterList = new ArrayList<Map<String, String>>();
					HashMap<String, String> mapValue = null;
					Integer[] equipmentIds = widget.getEquipment_Ids();
					List<Integer> intList = new ArrayList<>();
					for (int intValue : equipmentIds) {
						intList.add(intValue);
					}
					JSONArray parameters = new JSONArray();
					for (String element : widget.getMeasureParameter()) {
						parameters.put(element);
					}

					List<Object[]> arrayOfArrays = parameterComparisionService.getCompValue(widget.getSiteId(),
							widget.getRange(), timestamps[0], timestamps[1], intList, parameters);

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
					widget.setParameterList(parameterList);
				}
				
				userWidget.getWidgetList().add(widget);
			}
			
			return ResponseEntity.ok(userWidget);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
			
	}

	@GetMapping("/getWidgetsByUserId/{userId}")
	public ResponseEntity<List<Widgets>> getWidgetsByUserId(@PathVariable BigInteger userId,
			@RequestParam(required = false) String range) {
		try {
			System.out.println("Before calling getWidgetsByUserId: " + range);
			List<Widgets> widgets = widgetService.getWidgetsByUserId(userId);
			List<DailyGenerationTodayEnergyDTO> dailyGenValue = null;
			List<EnergyPerformanceDTO> energyGenValue = null;

			ObjectMapper objectMapper = new ObjectMapper();
			for (Widgets widget : widgets) {
				Date[] dateRange = setWidgetDateRange(widget.getTimePeriod());

				Timestamp[] timestamps = generateTimestamps(dateRange[0], dateRange[1]);
				widget.setFromDate(timestamps[0]);
				widget.setToDate(timestamps[1]);
				if (widget.getChartType().equals("Daily Generation")) {
					dailyGenValue = dailyGenerationService.getDgrValue(widget.getSiteId(), widget.getRange(),
							timestamps[0], timestamps[1]);
					widget.setDailygenerationChartData(dailyGenValue);
				} else if (widget.getChartType().equals("Energy Performance")) {
					Integer[] equipmentIds = widget.getEquipment_Ids();
					List<Integer> intList = new ArrayList<>();
					for (int intValue : equipmentIds) {
						intList.add(intValue);
					}
					energyGenValue = energyPerformanceService.getEnergyPerformanceValue(widget.getSiteId(),
							widget.getRange(), timestamps[0], timestamps[1], intList);
					widget.setEnergyPerformanceChartData(energyGenValue);
				} else if (widget.getChartType().equals("Parameter Comparision")) {
					List<Map<String, String>> parameterList = new ArrayList<Map<String, String>>();
					HashMap<String, String> mapValue = null;
					Integer[] equipmentIds = widget.getEquipment_Ids();
					List<Integer> intList = new ArrayList<>();
					for (int intValue : equipmentIds) {
						intList.add(intValue);
					}
					JSONArray parameters = new JSONArray();
					for (String element : widget.getMeasureParameter()) {
						parameters.put(element);
					}

					List<Object[]> arrayOfArrays = parameterComparisionService.getCompValue(widget.getSiteId(),
							widget.getRange(), timestamps[0], timestamps[1], intList, parameters);

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

					widget.setParameterList(parameterList);
				}
			}
			return ResponseEntity.ok(widgets);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/delete={widgetId}")
	public ResponseEntity<Object> deleteWidget(@PathVariable BigInteger widgetId) {
		widgetService.deleteWidgetById(widgetId);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/activate={widgetId}")
	public ResponseEntity<Object> activatewidgetById(@PathVariable Integer widgetId) {
		widgetService.activatewidgetById(widgetId);
		return ResponseEntity.ok("widget activated successfully");
	}

	@PutMapping("/deactivate={widgetId}")
	public ResponseEntity<Object> deactivatewidgetById(@PathVariable Integer widgetId) {
		widgetService.deactivatewidgetById(widgetId);
		return ResponseEntity.ok("widget deactivated successfully");
	}

	private Timestamp[] generateTimestamps(Date fromDate, Date toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String fromDateStr = dateFormat.format(fromDate);
		String toDateStr = dateFormat.format(toDate);

		String fromDateTimeStr = fromDateStr + " 00:05:00";
		String toDateTimeStr = toDateStr + " 13:30:00";

		try {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fromDateWithTime = dateTimeFormat.parse(fromDateTimeStr);
			Date toDateWithTime = dateTimeFormat.parse(toDateTimeStr);

			Timestamp fDate = new Timestamp(fromDateWithTime.getTime());
			Timestamp tDate = new Timestamp(toDateWithTime.getTime());

			return new Timestamp[] { fDate, tDate };
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Date[] setWidgetDateRange(String timePeriod) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date fromDate = null;
		Date toDate = null;

		if (timePeriod != null) {
			switch (timePeriod.toLowerCase()) {
			case "today":
				fromDate = new Date();
				toDate = new Date();
				break;
			case "yesterday":
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				fromDate = calendar.getTime();
				toDate = calendar.getTime();
				break;
			case "this month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				Date lastMonth = calendar.getTime();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				fromDate = calendar.getTime();
				toDate = lastMonth;
				break;
			case "last 30 days":
				calendar.add(Calendar.DAY_OF_MONTH, -30);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last 7 days":
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last week":
				int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				int daysToSunday = Calendar.SATURDAY - currentDayOfWeek + 1;
				calendar.add(Calendar.DAY_OF_MONTH, -daysToSunday - 6);
				fromDate = calendar.getTime();
				calendar.setTime(fromDate);
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				toDate = calendar.getTime();
				break;
			case "this week":
				int currentDayOfWeekThisWeek = calendar.get(Calendar.DAY_OF_WEEK);
				int daysToSaturday = Calendar.SATURDAY - currentDayOfWeekThisWeek;
				calendar.add(Calendar.DAY_OF_MONTH, -daysToSaturday);
				fromDate = calendar.getTime();
				calendar.setTime(fromDate);
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				toDate = calendar.getTime();
				break;
			default:
				fromDate = new Date();
				toDate = new Date();
				break;
			}
		} else {
			fromDate = new Date();
			toDate = new Date();
		}

		return new Date[] { fromDate, toDate };
	}
}
