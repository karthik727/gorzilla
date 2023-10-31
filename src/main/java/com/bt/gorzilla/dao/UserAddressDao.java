package com.bt.gorzilla.dao;

import java.util.List;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.entity.UserAddress;

public interface UserAddressDao {

	public List<UserAddress> getUserAddressDetails(PaginationBean paginationBean);

}
