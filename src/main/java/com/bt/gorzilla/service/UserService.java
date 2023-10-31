package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.bean.UserInputBean;
import com.bt.gorzilla.dao.UserDao;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.exception.UserRegistrationException;
import com.bt.gorzilla.response.UserResponse;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDao userDao;

	public UserResponse getAllUsers(Integer page, Integer size) {
		LOGGER.info("Inside getAllUsers");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		UserResponse userResponse = new UserResponse();
		List<User> userList = userDao.getUserDetails(paginationBean);
		userResponse.setUserData(userList);
		userResponse.setUserCount(userList.size());
		return userResponse;
	}
	
	public User getOneUser(String userName) {
		LOGGER.info("Inside getOneUser");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(null,null);
		User user = userDao.getSingleUserDetails(userName, paginationBean);
		return user;
	}

	public boolean createUser(UserInputBean userInputBean, PasswordEncoder passwordEncoder) throws UserRegistrationException {
		createActiveFlag(userInputBean);
		boolean isUserCreated = userDao.createUser(userInputBean,passwordEncoder);
		return isUserCreated;
	}

	private void createActiveFlag(UserInputBean userInputBean) {
		if (null != userInputBean && userInputBean.getIsActive() == null) {
			userInputBean.setIsActive("Y");
		}
	}

}
