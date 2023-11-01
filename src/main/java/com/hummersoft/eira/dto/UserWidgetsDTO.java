package com.hummersoft.eira.dto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hummersoft.eira.model.Widgets;

@Component
public class UserWidgetsDTO {
	
	private BigInteger userId;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public List<Widgets> getWidgetList() {
		return widgetList;
	}

	public void setWidgetList(List<Widgets> widgetList) {
		this.widgetList = widgetList;
	}

	public List<Map<String, Object>> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Map<String, Object>> siteList) {
		this.siteList = siteList;
	}

	public List<Map<String, Object>> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Map<String, Object>> equipmentList) {
		this.equipmentList = equipmentList;
	}

	List<Widgets> widgetList;
	
	List<Map<String, Object>> siteList;
	
	List<Map<String, Object>> equipmentList;
	
}
