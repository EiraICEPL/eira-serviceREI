package com.hummersoft.eira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.model.Widgets;

@RestController
@RequestMapping("/eira/alerts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomizedAlertController {

	@GetMapping("/lastupdated")
	public ResponseEntity<Widgets> getLastUpdatedAlerts() {

		return null;
	}
}
