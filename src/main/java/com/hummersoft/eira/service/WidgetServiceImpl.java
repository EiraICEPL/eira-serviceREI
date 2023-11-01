package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.model.Widgets;
import com.hummersoft.eira.repository.WidgetRepository;

import jakarta.transaction.Transactional;

@Service
public class WidgetServiceImpl implements WidgetServices {

	@Autowired
	private WidgetRepository widgetRepository;

	@Transactional
	@Override
	public Widgets saveWidget(Widgets widget) {
		return widgetRepository.save(widget);
	}

	@Override
	@Transactional
	public List<Widgets> getWidgetsByUserId(BigInteger userId) {
		List<Widgets> widgets = widgetRepository.findByUserId(userId);

		return widgets;
	}

	@Transactional
	@Override
	public void deleteWidgetById(BigInteger widgetId) {
		widgetRepository.deleteById(widgetId);

	}

	@Transactional
	@Override
	public void activatewidgetById(Integer widgetId) {
		widgetRepository.activatewidgetById(widgetId);
	}

	@Transactional
	@Override
	public void deactivatewidgetById(Integer widgetId) {
		widgetRepository.deactivatewidgetById(widgetId);

	}

	@Override
	public List<Widgets> getWidgetsBySiteId(BigInteger userId, BigInteger siteId) {
		List<Widgets> widgets = widgetRepository.findByUserIdandSiteId(userId, siteId);

		return widgets;
	}
}
