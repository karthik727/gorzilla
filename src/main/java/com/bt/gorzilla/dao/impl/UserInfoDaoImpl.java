package com.bt.gorzilla.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.bean.UserInfoRegisterBean;
import com.bt.gorzilla.dao.UserInfoDao;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.entity.UserInfo;
import com.bt.gorzilla.exception.UserRegistrationException;

//Spring data jpa has vulnerabilities using native daoimpl
//https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.1.5
@Repository
public class UserInfoDaoImpl   implements UserInfoDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<UserInfo> getUserInfoDetails(PaginationBean paginationBean) {
		LOGGER.info("Iside getUserInfoDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		UserInfo userInfo;
		List<UserInfo> userInfoList = new LinkedList<UserInfo>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER_INFO.USERINFOID, USER_INFO.USERID, USER_INFO.EMAILID, USER_INFO.FIRSTNAME, USER_INFO.MIDDLENAME, USER_INFO.LASTNAME, USER_INFO.CREATEDBY, USER_INFO.CREATEDDATE, USER_INFO.LASTUPDATEDBY, USER_INFO.LASTUPDATEDDATE, USER_INFO.LASTUPDATEDLOGIN FROM USER_INFO LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER_INFO.USERINFOID, USER_INFO.USERID, USER_INFO.EMAILID, USER_INFO.FIRSTNAME, USER_INFO.MIDDLENAME, USER_INFO.LASTNAME, USER_INFO.CREATEDBY, USER_INFO.CREATEDDATE, USER_INFO.LASTUPDATEDBY, USER_INFO.LASTUPDATEDDATE, USER_INFO.LASTUPDATEDLOGIN FROM USER_INFO");
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserInfoId(rs.getInt(1));
				userInfo.setUserId(rs.getInt(2));
				userInfo.setEmailId(rs.getString(3));
				userInfo.setFirstName(rs.getString(4));
				userInfo.setMiddleName(rs.getString(5));
				userInfo.setLastName(rs.getString(6));
				userInfo.setCreatedBy(rs.getString(7));
				userInfo.setCreatedDate(rs.getDate(8));
				userInfo.setLastUpdatedBy(rs.getString(9));
				userInfo.setLastUpdateDate(rs.getDate(10));
				userInfo.setLastUpdatedLogin(rs.getString(11));
				userInfoList.add(userInfo);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching user info data", e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user info data closing prepared statement", e.getMessage());
			}
		}

		return userInfoList;
	}

	@Override
	public UserInfo getSingleUserInfoDetails(String userId) {
		LOGGER.info("Inside getSingleUserInfoDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		UserInfo userInfo = null;
		try (Connection connection = dataSource.getConnection()) {
			userPreparedStatement = connection.prepareStatement(
						"SELECT USER_INFO.USERINFOID, USER_INFO.USERID, USER_INFO.EMAILID, USER_INFO.FIRSTNAME, USER_INFO.MIDDLENAME, USER_INFO.LASTNAME, USER_INFO.CREATEDBY, USER_INFO.CREATEDDATE, USER_INFO.LASTUPDATEDBY, USER_INFO.LASTUPDATEDDATE, USER_INFO.LASTUPDATEDLOGIN FROM USER_INFO WHERE userId = ? ");
			userPreparedStatement.setString(1, userId);
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt(1));
				userInfo.setUserId(rs.getInt(2));
				userInfo.setEmailId(rs.getString(3));
				userInfo.setFirstName(rs.getString(4));
				userInfo.setMiddleName(rs.getString(5));
				userInfo.setLastName(rs.getString(6));
				userInfo.setCreatedBy(rs.getString(7));
				userInfo.setCreatedDate(rs.getDate(8));
				userInfo.setLastUpdatedBy(rs.getString(9));
				userInfo.setLastUpdateDate(rs.getDate(10));
				userInfo.setLastUpdatedLogin(rs.getString(11));
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching user data", e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user data closing prepared statement", e.getMessage());
			}
		}
		return userInfo;
	}

	@Override
	public void createUserInfo(UserInfoRegisterBean userInfoRegisterBean, User user) throws UserRegistrationException {
		LOGGER.info("Inside createUserInfo DaoImpl");
		PreparedStatement userPreparedStatement = null;
		java.util.Date javaDate = new java.util.Date();
		java.sql.Date date = new java.sql.Date(javaDate.getTime());
		try (Connection connection = dataSource.getConnection()) {
			userPreparedStatement = connection.prepareStatement(
					"INSERT INTO USER_INFO(USERID,EMAILID,FIRSTNAME,MIDDLENAME,LASTNAME,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?, ?, ?,?, ?, ?, ?,?,?,?)");
			userPreparedStatement.setInt(1, userInfoRegisterBean.getUserId());
			userPreparedStatement.setString(2, userInfoRegisterBean.getEmailId());
			userPreparedStatement.setString(3, userInfoRegisterBean.getFirstName());
			userPreparedStatement.setString(4, userInfoRegisterBean.getMiddleName());
			userPreparedStatement.setString(5, userInfoRegisterBean.getLastName());
			userPreparedStatement.setString(6, user.getUserName());
			userPreparedStatement.setDate(7, date);
			userPreparedStatement.setString(8, user.getUserName());
			userPreparedStatement.setDate(9, date);
			userPreparedStatement.setString(10, user.getUserName());
			int status = userPreparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error occured while inserting user info data", e.getMessage());
			e.printStackTrace();
			throw new UserRegistrationException("Error while storing user info data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user info data closing prepared statement", e.getMessage());
			}
		}
	}
	
}