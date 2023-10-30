package com.bt.gorzilla.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bt.gorzilla.Exception.UserRegistrationException;
import com.bt.gorzilla.bean.UserRegisterBean;
import com.bt.gorzilla.dao.UserRegisterDao;

//Spring data jpa has vulnerabilities hence created daoimpl
@Repository
public class UserRegisterDaoImpl  implements UserRegisterDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public boolean registerUser(UserRegisterBean userRegisterBean, PasswordEncoder passwordEncoder) throws UserRegistrationException {
		LOGGER.info("Inside registerUser DaoImpl");	
		PreparedStatement userPreparedStatement = null;
		Connection connection = null;
		try {		
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
	        java.util.Date javaDate = new java.util.Date();
	        java.sql.Date date = new java.sql.Date(javaDate.getTime());
			createUser(connection,userRegisterBean,passwordEncoder,date);
			Integer userId = getUserId(connection,userRegisterBean.getUserName());
			createUserInfo(connection,userRegisterBean,userId,date);
			createUserAddress(connection,userRegisterBean,userId,date);
			connection.commit();
		} catch (Exception e) {
			LOGGER.error("Error occured while inserting user data",e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			e.printStackTrace();
			throw new UserRegistrationException("Error while storing user data");
		} finally {
			try {
				if (null != connection && !connection.isClosed()) {
					connection.close();
				}
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user data closing prepared statement",e.getMessage());
			}
		}
		return true;
	}

	private void createUser(Connection connection, UserRegisterBean userRegisterBean, PasswordEncoder passwordEncoder, java.sql.Date date) throws UserRegistrationException {
		PreparedStatement userPreparedStatement = null;
	    try {
			userPreparedStatement = connection.prepareStatement(
				"INSERT INTO USER(USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?, ?, ?, ?,?, ?, ?, ?)");
				userPreparedStatement.setString(1, userRegisterBean.getUserName());
				LOGGER.info("passwordEncoder.encode(user.getPassword():"+passwordEncoder.encode(userRegisterBean.getPassword()));
				userPreparedStatement.setString(2, passwordEncoder.encode(userRegisterBean.getPassword()));
				userPreparedStatement.setString(3, userRegisterBean.getIsActive());
				userPreparedStatement.setString(4, userRegisterBean.getUserName());
				userPreparedStatement.setDate(5, date);
				userPreparedStatement.setString(6, userRegisterBean.getUserName());
				userPreparedStatement.setDate(7, date);
				userPreparedStatement.setString(8, userRegisterBean.getUserName());
			int status = userPreparedStatement.executeUpdate();
			if(status == 0) {
				throw new UserRegistrationException("Error while storing user data");
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error occured while saving user data",e.getMessage());
			e.printStackTrace();
			throw new UserRegistrationException("Error while storing user data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing user data closing prepared statement",e.getMessage());
			}
		}
	}
	
	private Integer getUserId(Connection connection, String userName) throws UserRegistrationException {
		PreparedStatement userPreparedStatement = null;
		Integer userId = null;
		try {
			userPreparedStatement = connection.prepareStatement(
				"SELECT USERID FROM USER WHERE USERNAME = ?");
				userPreparedStatement.setString(1, userName);
				ResultSet rs = userPreparedStatement.executeQuery();
				if(rs == null) {
					throw new UserRegistrationException("Error while fetching userid from username");
				}
				while (rs.next()) {
					return rs.getInt(1);
				}
		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching user data",e.getMessage());
			e.printStackTrace();
			throw new UserRegistrationException("Error while fetching user data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user data closing prepared statement",e.getMessage());
			}
		}
		return userId;
	}

	private void createUserInfo(Connection connection, UserRegisterBean userRegisterBean, Integer userId, java.sql.Date date) throws UserRegistrationException {
		PreparedStatement userPreparedStatement = null;
		try{
			userPreparedStatement = connection.prepareStatement(
				"INSERT INTO USER_INFO (USERID, EMAILID, FIRSTNAME,MIDDLENAME,LASTNAME,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				userPreparedStatement.setInt(1, userId);
				userPreparedStatement.setString(2, userRegisterBean.getEmailId());
				userPreparedStatement.setString(3, userRegisterBean.getFirstName());
				userPreparedStatement.setString(4, userRegisterBean.getMiddleName());
				userPreparedStatement.setString(5, userRegisterBean.getMiddleName());
				userPreparedStatement.setString(6, userRegisterBean.getUserName());
				userPreparedStatement.setDate(7, date);
				userPreparedStatement.setString(8, userRegisterBean.getUserName());
				userPreparedStatement.setDate(9, date);
				userPreparedStatement.setString(10, userRegisterBean.getUserName());
			int status = userPreparedStatement.executeUpdate();
			if(status == 0) {
				throw new UserRegistrationException("Error while storing user info data");
			}
		} catch (SQLException e) {
			LOGGER.error("Error occured while storing user info data",e.getMessage());
			e.printStackTrace();
			throw new UserRegistrationException("Error while storing user info data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing user info data closing prepared statement",e.getMessage());
			}
		}
	}
	
	private void createUserAddress(Connection connection, UserRegisterBean userRegisterBean, Integer userId, java.sql.Date date) throws UserRegistrationException {
		PreparedStatement userPreparedStatement = null;
		try{
			userPreparedStatement = connection.prepareStatement(
				"INSERT INTO USER_ADDRESS (USERID, ADDRESS, CITY,PINCODE,COUNTRY,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				userPreparedStatement.setInt(1, userId);
				userPreparedStatement.setString(2, userRegisterBean.getAddress());
				userPreparedStatement.setString(3, userRegisterBean.getCity());
				userPreparedStatement.setString(4, userRegisterBean.getPincode());
				userPreparedStatement.setString(5, userRegisterBean.getCountry());
				userPreparedStatement.setString(6, userRegisterBean.getUserName());
				userPreparedStatement.setDate(7, date);
				userPreparedStatement.setString(8, userRegisterBean.getUserName());
				userPreparedStatement.setDate(9, date);
				userPreparedStatement.setString(10, userRegisterBean.getUserName());
			int status = userPreparedStatement.executeUpdate();
			if(status == 0) {
				throw new UserRegistrationException("Error while storing user address data");
			}
		} catch (SQLException e) {
			LOGGER.error("Error occured while storing user address data",e.getMessage());
			e.printStackTrace();
			throw new UserRegistrationException("Error while storing user address data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing user address data closing prepared statement",e.getMessage());
			}
		}
	}




}
