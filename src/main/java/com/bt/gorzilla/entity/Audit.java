package com.bt.gorzilla.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Audit {

	private String createdBy;
	private Date createdDate;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	private String lastUpdatedLogin;

	@Column(name = "CREATEDBY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATEDDATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LASTUPDATEDBY")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "LASTUPDATEDDATE")
	public Date getLastUpdateDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdatedDate = lastUpdateDate;
	}

	@Column(name = "LASTUPDATEDLOGIN")
	public String getLastUpdatedLogin() {
		return lastUpdatedLogin;
	}

	public void setLastUpdatedLogin(String lastUpdatedLogin) {
		this.lastUpdatedLogin = lastUpdatedLogin;
	}

}