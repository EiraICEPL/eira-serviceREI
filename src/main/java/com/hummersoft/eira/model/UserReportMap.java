package com.hummersoft.eira.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

//import com.hummersoft.eira.Entity.ReportMaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "userreportmap",schema="eampm")
public class UserReportMap implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportmapid")
    private Integer reportMapId;
    
    @Column(name="userid")
    private Integer userId;
    
    @Column(name="reportid")
    private Integer reportId;
    
    @Column(name="timeperiod")
    private String timePeriod;
    
    @Column(name="mailid")
    private String mailId;
    
    @Column(name="activeflag")
    private Integer activeFlag;
    
    @Column(name="creationdate")
    private Timestamp CreationDate;
    
    @Column(name="range")
    private String range;
    
    @Column(name="siteid")
    private Integer siteId;
    @ManyToOne
    @JoinColumn(name = "reportid", referencedColumnName = "reportid", insertable = false, updatable = false)
    private ReportMaster report;

    public String getReportName() {
        return report != null ? report.getReportName() : null;
    }
    
    @ManyToOne
    @JoinColumn(name = "siteid", referencedColumnName = "siteid", insertable = false, updatable = false)
    private Site site;

    @Transient
    private String siteName; // Transient field to hold site name from Site entity

    
    public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteName() {
        if (site != null) {
            siteName = site.getSiteName();
        }
        return siteName;
    }
	public Integer getReportMapId() {
		return reportMapId;
	}

	public void setReportMapId(Integer reportMapId) {
		this.reportMapId = reportMapId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Timestamp getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		CreationDate = creationDate;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
    
    
    
}
