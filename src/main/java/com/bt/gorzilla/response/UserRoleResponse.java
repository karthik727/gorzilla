package com.bt.gorzilla.response;

import java.util.List;

import com.bt.gorzilla.entity.UserRole;

public class UserRoleResponse {

	private List<UserRole> userRoleData;
	private Integer userRoleCount;

	public List<UserRole> getUserRoleData() {
		return userRoleData;
	}

	public void setUserRoleData(List<UserRole> userRoleData) {
		this.userRoleData = userRoleData;
	}

	public Integer getUserRoleCount() {
		return userRoleCount;
	}

	public void setUserRoleCount(Integer userRoleCount) {
		this.userRoleCount = userRoleCount;
	}

}
