package com.bt.gorzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.dao.RoleDao;
import com.bt.gorzilla.entity.Role;
import com.bt.gorzilla.response.RoleResponse;
import com.bt.gorzilla.util.PaginationUtil;

@Service
public class RoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	RoleDao roleDao;

	public RoleResponse getAllRoles(Integer page, Integer size) {
		LOGGER.info("Inside getAllRoles");
		PaginationBean paginationBean = PaginationUtil.calculatePagination(page,size);
		RoleResponse roleResponse = new RoleResponse();
		List<Role> roleList = roleDao.getAllRoles(paginationBean);
		roleResponse.setRoleData(roleList);
		roleResponse.setRoleCount(roleList.size());
		return roleResponse;
	}


}
