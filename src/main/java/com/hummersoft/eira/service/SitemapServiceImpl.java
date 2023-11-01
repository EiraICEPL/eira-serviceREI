package com.hummersoft.eira.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.repository.SitemapRepository;

import jakarta.transaction.Transactional;

@Service
public class SitemapServiceImpl implements SitemapService {

	@Autowired
	private SitemapRepository sitemapRepository;

	
	@Override
	@Transactional
    public List<Map<String, Object>> getSiteIdAndSiteNameByUserId(Integer userId) {
        List<Object[]> siteInfo = sitemapRepository.getSiteIdAndSiteNameByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : siteInfo) {
            Map<String, Object> siteMap = new HashMap<>();
            siteMap.put("siteId", row[0]);
            siteMap.put("siteName", row[1]);
            result.add(siteMap);
        }

        return result;
    }

}
