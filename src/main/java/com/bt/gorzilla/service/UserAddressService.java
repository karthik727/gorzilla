package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.dao.UserAddressDao;
import com.bt.gorzilla.entity.UserAddress;
import com.bt.gorzilla.response.UserAddressResponse;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class UserAddressService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressService.class);
	
	@Autowired
	UserAddressDao userAddressDao;

	public UserAddressResponse getAllUsersAddress(Integer page, Integer size) {
		LOGGER.info("Inside getAllUsersAddress");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		UserAddressResponse userAddressResponse = new UserAddressResponse();
		List<UserAddress> userInfoList = userAddressDao.getUserAddressDetails(paginationBean);
		userAddressResponse.setUserAddressData(userInfoList);
		userAddressResponse.setUserAddressCount(userInfoList.size());
		return userAddressResponse;
	}

}
