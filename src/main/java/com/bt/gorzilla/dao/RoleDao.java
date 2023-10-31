package com.bt.gorzilla.dao;

import java.util.List;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.entity.Role;

public interface RoleDao {

	List<Role> getAllRoles(PaginationBean paginationBean);

}
