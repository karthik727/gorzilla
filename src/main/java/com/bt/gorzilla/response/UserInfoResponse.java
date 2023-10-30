package com.bt.gorzilla.response;

import java.util.List;

import com.bt.gorzilla.entity.UserInfo;

public class UserInfoResponse {

	private List<UserInfo> userInfoData;
	private Integer userInfoCount;

	public List<UserInfo> getUserInfoData() {
		return userInfoData;
	}

	public void setUserInfoData(List<UserInfo> userInfoData) {
		this.userInfoData = userInfoData;
	}

	public Integer getUserInfoCount() {
		return userInfoCount;
	}

	public void setUserInfoCount(Integer userInfoCount) {
		this.userInfoCount = userInfoCount;
	}

}
