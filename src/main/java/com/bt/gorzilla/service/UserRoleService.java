package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.dao.UserRoleDao;
import com.bt.gorzilla.entity.UserRole;
import com.bt.gorzilla.response.UserRoleResponse;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class UserRoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleService.class);
	
	@Autowired
	UserRoleDao userRoleDao;

	public UserRoleResponse getUserRoles(Integer page, Integer size) {
		LOGGER.info("Inside getUserRoles");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		UserRoleResponse userRoleResponse = new UserRoleResponse();
		List<UserRole> userRoleList = userRoleDao.getUserRoles(paginationBean);
		userRoleResponse.setUserRoleData(userRoleList);
		userRoleResponse.setUserRoleCount(userRoleList.size());
		return userRoleResponse;
	}
}
