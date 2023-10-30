package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.Exception.UserRegistrationException;
import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.bean.UserInfoRegisterBean;
import com.bt.gorzilla.dao.UserDao;
import com.bt.gorzilla.dao.UserInfoDao;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.entity.UserInfo;
import com.bt.gorzilla.response.UserInfoResponse;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class UserInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	UserDao userDao;

	public UserInfoResponse getAllUsersInfo(Integer page, Integer size) {
		LOGGER.info("Inside getAllUsersInfo");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		List<UserInfo> userInfoList = userInfoDao.getUserInfoDetails(paginationBean);
		userInfoResponse.setUserInfoData(userInfoList);
		userInfoResponse.setUserInfoCount(userInfoList.size());
		return userInfoResponse;
	}
	
	public UserInfo getOneUserInfo(String userId) {
		LOGGER.info("Inside getOneUserInfo");
		UserInfo UserInfo = userInfoDao.getSingleUserInfoDetails(userId);
		return UserInfo;
	}

	public void createUserInfo(UserInfoRegisterBean userInfoRegisterBean) throws UserRegistrationException {
		User user = userDao.getUserDetailsById(userInfoRegisterBean.getUserId(), null);
		if(null == user || null == user.getUserName() || user.getUserName().isEmpty()) {
			throw new UserRegistrationException("User id is not found");
		}
		userInfoDao.createUserInfo(userInfoRegisterBean,user);
	}


}
