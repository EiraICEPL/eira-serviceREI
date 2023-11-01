package com.hummersoft.eira.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.service.LoggerService;

@RestController
@RequestMapping("/eira/dataLogger")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoggerController {
	@Autowired
	private LoggerService loggerService;

	@GetMapping("/site={siteId}")
	public ResponseEntity<List<Object>> getParameterNamesBySiteId(@PathVariable BigInteger siteId) {
	    List<Object> parameterNames = loggerService.getParameterNamesBySiteId(siteId);
	    return ResponseEntity.ok(parameterNames);
	}

	
}