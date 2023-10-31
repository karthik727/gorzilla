package com.bt.gorzilla.response;

import java.util.List;

import com.bt.gorzilla.entity.UserAddress;

public class UserAddressResponse {

	private List<UserAddress> userAddressData;
	private Integer userAddressCount;

	public List<UserAddress> getUserAddressData() {
		return userAddressData;
	}

	public void setUserAddressData(List<UserAddress> userAddressData) {
		this.userAddressData = userAddressData;
	}

	public Integer getUserAddressCount() {
		return userAddressCount;
	}

	public void setUserAddressCount(Integer userAddressCount) {
		this.userAddressCount = userAddressCount;
	}

}
