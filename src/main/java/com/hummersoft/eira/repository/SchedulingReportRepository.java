package com.hummersoft.eira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.UserReportMap;


@Repository
public interface SchedulingReportRepository extends JpaRepository<UserReportMap, Integer> {

    @Query(value = "SELECT * FROM eampm.userreportmap WHERE timeperiod = :timeperiod", nativeQuery = true)
    List<UserReportMap> findByTimePeriod(@Param("timeperiod") String timeperiod);
}