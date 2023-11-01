package com.hummersoft.eira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hummersoft.eira.model.DataSource;

public interface DataSourceRepository extends JpaRepository<DataSource, Integer> {
	
	
	@Query(value="select * from tdatasource where siteid =:siteId and  equipmentid in :equipId"
			+ "  order by equipmentid, timestamp ", nativeQuery=true)
	List<DataSource> getEnergyDetails(@Param("siteId") int siteId, @Param("equipId") List<Integer> equipId);

}
