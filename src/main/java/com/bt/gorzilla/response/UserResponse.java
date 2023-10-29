package com.bt.gorzilla.response;

import java.io.Serializable;
import java.util.List;

import com.bt.gorzilla.entity.User;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 3594469801875996121L;

	private List<User> userData;
	private Integer userCount;

	public List<User> getUserData() {
		return userData;
	}

	public void setUserData(List<User> userData) {
		this.userData = userData;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

}
