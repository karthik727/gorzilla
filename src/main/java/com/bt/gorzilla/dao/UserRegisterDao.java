package com.bt.gorzilla.dao;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bt.gorzilla.Exception.UserRegistrationException;
import com.bt.gorzilla.bean.UserRegisterBean;

public interface UserRegisterDao {

	boolean registerUser(UserRegisterBean userRegisterBean, PasswordEncoder passwordEncoder) throws UserRegistrationException;

}
