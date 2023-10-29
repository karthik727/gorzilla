package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.dao.UserDao;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDao userDao;

	public List<User> getUserDetailsService(Integer page, Integer size) {
		LOGGER.info("Inside getUserDetailsService");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		List<User> userList = userDao.getUserDetails(paginationBean);
		return userList;
	}

}
