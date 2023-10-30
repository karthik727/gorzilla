package com.bt.gorzilla.dao;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bt.gorzilla.Exception.UserRegistrationException;
import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.bean.UserInputBean;
import com.bt.gorzilla.entity.User;

public interface UserDao {

	public List<User> getUserDetails(PaginationBean paginationBean);
	
	public User getSingleUserDetails(String userName,PaginationBean paginationBean);
	
	public User getUserDetailsById(Integer userId,PaginationBean paginationBean);

	public boolean createUser(UserInputBean userInputBean, PasswordEncoder passwordEncoder) throws UserRegistrationException;

}
