package com.hummersoft.eira.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.service.SitemapService;

@RestController
@RequestMapping("/eira")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SitemapController {
	@Autowired
	private SitemapService sitemapService;

	@GetMapping("/getSiteByUserId={userId}")
	public ResponseEntity<List<Map<String, Object>>> getSiteByUserId(@PathVariable Integer userId) {
	    try {
	        List<Map<String, Object>> siteInfo = sitemapService.getSiteIdAndSiteNameByUserId(userId);

	        return ResponseEntity.ok(siteInfo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
