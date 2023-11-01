package com.hummersoft.eira.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.Widgets;

@Repository
public interface WidgetRepository extends JpaRepository<Widgets, BigInteger> {
	
	 @Query(value = "SELECT * FROM eampm.widgets WHERE userId = :userId AND activeflag = 1", nativeQuery = true)
	List<Widgets> findByUserId(@Param("userId") BigInteger userId);
	 
	 @Query(value = "SELECT * FROM eampm.widgets WHERE userId = :userId AND siteid=:siteId and activeflag = 1", nativeQuery = true)
	List<Widgets> findByUserIdandSiteId(@Param("userId") BigInteger userId, @Param("siteId") BigInteger siteId);

	@Modifying
	@Query(value = "UPDATE eampm.widgets SET activeflag = 1 WHERE widgetId = :widgetId", nativeQuery = true)
	void activatewidgetById(@Param("widgetId") Integer widgetId);
	
	@Modifying
	@Query(value = "UPDATE eampm.widgets SET activeflag = 0 WHERE widgetId = :widgetId", nativeQuery = true)
	void deactivatewidgetById(@Param("widgetId") Integer widgetId);

}
