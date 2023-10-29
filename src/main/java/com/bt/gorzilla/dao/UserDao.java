package com.bt.gorzilla.dao;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.entity.User;

public interface UserDao {

	public List<User> getUserDetails(PaginationBean paginationBean);
	
	public User getSingleUserDetails(String userName,PaginationBean paginationBean);

	public boolean createUser(User user, PasswordEncoder passwordEncoder);

}
