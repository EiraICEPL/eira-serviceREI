package com.hummersoft.eira.service;

import java.util.List;
import java.util.Map;

public interface SitemapService {


	List<Map<String, Object>> getSiteIdAndSiteNameByUserId(Integer userId);

}
