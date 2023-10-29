package com.bt.gorzilla.entity;

import java.util.Date;

public class Audit {

	private String createdBy;
	private Date createdDate;
	private String lastUpdatedBy;
	private Date lastUpdateDate;
	private String lastUpdatedLogin;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedLogin() {
		return lastUpdatedLogin;
	}

	public void setLastUpdatedLogin(String lastUpdatedLogin) {
		this.lastUpdatedLogin = lastUpdatedLogin;
	}

}
