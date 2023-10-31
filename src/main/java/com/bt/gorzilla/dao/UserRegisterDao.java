package com.bt.gorzilla.dao;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bt.gorzilla.bean.UserRegisterBean;
import com.bt.gorzilla.exception.UserRegistrationException;

public interface UserRegisterDao {

	boolean registerUser(UserRegisterBean userRegisterBean, PasswordEncoder passwordEncoder) throws UserRegistrationException;

}
