package com.bt.gorzilla.response;

import java.util.List;

import com.bt.gorzilla.entity.Role;

public class RoleResponse {

	private List<Role> roleData;
	private Integer roleCount;

	public List<Role> getRoleData() {
		return roleData;
	}

	public void setRoleData(List<Role> roleData) {
		this.roleData = roleData;
	}

	public Integer getRoleCount() {
		return roleCount;
	}

	public void setRoleCount(Integer roleCount) {
		this.roleCount = roleCount;
	}

}
