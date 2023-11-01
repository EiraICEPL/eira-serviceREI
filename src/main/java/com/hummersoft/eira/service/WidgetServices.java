package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;

import com.hummersoft.eira.model.Widgets;

public interface WidgetServices {

	Widgets saveWidget(Widgets widget);

	List<Widgets> getWidgetsByUserId(BigInteger userId);
	
	List<Widgets> getWidgetsBySiteId(BigInteger userId,BigInteger siteId);

	void deleteWidgetById(BigInteger widgetId);

	void activatewidgetById(Integer widgetId);

	void deactivatewidgetById(Integer widgetId);


}
