package com.bt.gorzilla.dao;

import java.util.List;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.bean.UserInfoRegisterBean;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.entity.UserInfo;
import com.bt.gorzilla.exception.UserRegistrationException;

public interface UserInfoDao {

	public List<UserInfo> getUserInfoDetails(PaginationBean paginationBean);

	public UserInfo getSingleUserInfoDetails(String userId);

	public void createUserInfo(UserInfoRegisterBean userInfoRegisterBean, User user) throws UserRegistrationException;

}
