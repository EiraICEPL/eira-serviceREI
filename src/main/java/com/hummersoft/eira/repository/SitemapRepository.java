package com.hummersoft.eira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.Site;

@Repository
public interface SitemapRepository extends JpaRepository<Site, Integer> {
	@Query(value = "SELECT msite.siteId, msite.siteName " +
	        "FROM eampm.msite " +
	        "JOIN eampm.msitemap ON msitemap.siteid = msite.siteid " +
	        "WHERE msitemap.userId = :userId " +
	        "AND msite.activeflag = 1 AND msitemap.activeflag=1 order by siteName asc",
	        nativeQuery = true)
	List<Object[]> getSiteIdAndSiteNameByUserId(@Param("userId") Integer userId);

}
