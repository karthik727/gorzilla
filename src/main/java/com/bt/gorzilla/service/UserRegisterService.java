package com.bt.gorzilla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.UserRegisterBean;
import com.bt.gorzilla.dao.UserRegisterDao;
import com.bt.gorzilla.exception.UserRegistrationException;

@Service
public class UserRegisterService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterService.class);
	
	@Autowired
	UserRegisterDao userRegisterDao;

	
	public boolean registerUser(UserRegisterBean userRegisterBean, PasswordEncoder passwordEncoder) throws UserRegistrationException {
		boolean isUserCreated = userRegisterDao.registerUser(userRegisterBean,passwordEncoder);
		return isUserCreated;
	}

	
}
