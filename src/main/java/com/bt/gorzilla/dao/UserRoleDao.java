package com.bt.gorzilla.dao;

import java.util.List;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.entity.UserRole;

public interface UserRoleDao {

	List<UserRole> getUserRoles(PaginationBean paginationBean);

}
