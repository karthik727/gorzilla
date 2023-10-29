package com.bt.gorzilla.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bt.gorzilla.bean.PaginationBean;
import com.bt.gorzilla.dao.UserDao;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.entity.UserAddress;
import com.bt.gorzilla.entity.UserInfo;

//Spring data jpa has vulnerabilities hence created daoimpl
@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<User> getUserDetails(PaginationBean paginationBean) {
		LOGGER.info("Iside getUserDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		User user;
		UserInfo userInfo;
		UserAddress userAddress;
		List<User> userList = new LinkedList<User>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER.USERID, USER.USERNAME, USER.PASSWORD, USER.ISACTIVE, USER.CREATEDBY, USER.CREATEDDATE, USER.LASTUPDATEDBY, USER.LASTUPDATEDDATE, USER.LASTUPDATEDLOGIN, USER_INFO.USERINFOID, USER_INFO.USERID, USER_INFO.EMAILID, USER_INFO.FIRSTNAME, USER_INFO.MIDDLENAME, USER_INFO.LASTNAME, USER_INFO.CREATEDBY, USER_INFO.CREATEDDATE, USER_INFO.LASTUPDATEDBY, USER_INFO.LASTUPDATEDDATE, USER_INFO.LASTUPDATEDLOGIN, USER_ADDRESS.USERADDRESSID, USER_ADDRESS.USERID, USER_ADDRESS.ADDRESS, USER_ADDRESS.CITY, USER_ADDRESS.PINCODE, USER_ADDRESS.COUNTRY, USER_ADDRESS.CREATEDBY, USER_ADDRESS.CREATEDDATE, USER_ADDRESS.LASTUPDATEDBY, USER_ADDRESS.LASTUPDATEDDATE, USER_ADDRESS.LASTUPDATEDLOGIN FROM ((USER JOIN USER_INFO ON USER.USERID = USER_INFO.USERID) JOIN USER_ADDRESS ON USER.USERID = USER_ADDRESS.USERID) LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER.USERID, USER.USERNAME, USER.PASSWORD, USER.ISACTIVE, USER.CREATEDBY, USER.CREATEDDATE, USER.LASTUPDATEDBY, USER.LASTUPDATEDDATE, USER.LASTUPDATEDLOGIN, USER_INFO.USERINFOID, USER_INFO.USERID, USER_INFO.EMAILID, USER_INFO.FIRSTNAME, USER_INFO.MIDDLENAME, USER_INFO.LASTNAME, USER_INFO.CREATEDBY, USER_INFO.CREATEDDATE, USER_INFO.LASTUPDATEDBY, USER_INFO.LASTUPDATEDDATE, USER_INFO.LASTUPDATEDLOGIN, USER_ADDRESS.USERADDRESSID, USER_ADDRESS.USERID, USER_ADDRESS.ADDRESS, USER_ADDRESS.CITY, USER_ADDRESS.PINCODE, USER_ADDRESS.COUNTRY, USER_ADDRESS.CREATEDBY, USER_ADDRESS.CREATEDDATE, USER_ADDRESS.LASTUPDATEDBY, USER_ADDRESS.LASTUPDATEDDATE, USER_ADDRESS.LASTUPDATEDLOGIN FROM ((USER JOIN USER_INFO ON USER.USERID = USER_INFO.USERID) JOIN USER_ADDRESS ON USER.USERID = USER_ADDRESS.USERID)");
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIsActive(rs.getString(4));
				user.setCreatedBy(rs.getString(5));
				user.setCreatedDate(rs.getDate(6));
				user.setLastUpdatedBy(rs.getString(7));
				user.setLastUpdateDate(rs.getDate(8));
				user.setLastUpdatedLogin(rs.getString(9));
				userInfo = new UserInfo();
				userInfo.setUserInfoId(rs.getInt(10));
				userInfo.setUserId(rs.getInt(11));
				userInfo.setEmailId(rs.getString(12));
				userInfo.setFirstName(rs.getString(13));
				userInfo.setMiddleName(rs.getString(14));
				userInfo.setLastName(rs.getString(15));
				userInfo.setCreatedBy(rs.getString(16));
				userInfo.setCreatedDate(rs.getDate(17));
				userInfo.setLastUpdatedBy(rs.getString(18));
				userInfo.setLastUpdateDate(rs.getDate(19));
				userInfo.setLastUpdatedLogin(rs.getString(20));
				user.setUserInfo(userInfo);
				userAddress = new UserAddress();
				userAddress.setUserAddressId(rs.getInt(21));
				userAddress.setUserId(rs.getInt(22));
				userAddress.setAddress(rs.getString(23));
				userAddress.setCity(rs.getString(24));
				userAddress.setPincode(rs.getString(25));
				userAddress.setCountry(rs.getString(26));
				userAddress.setCreatedBy(rs.getString(27));
				userAddress.setCreatedDate(rs.getDate(28));
				userAddress.setLastUpdatedBy(rs.getString(29));
				userAddress.setLastUpdateDate(rs.getDate(30));
				userAddress.setLastUpdatedLogin(rs.getString(31));
				user.setUserAddress(userAddress);				
				userList.add(user);
			}

		} catch (SQLException e) {
			LOGGER.info("Error occured while fetching user data",e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.info("Error occured while fetching user data closing prepared statement",e.getMessage());
			}
		}

		return userList;
	}

	@Override
	public User getSingleUserDetails(String userName, PaginationBean paginationBean) {
		LOGGER.info("Inside getSingleUserDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		User user = null;
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERID,USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER WHERE USERNAME = ? LIMIT ? OFFSET ?");
				userPreparedStatement.setString(1, userName);
				userPreparedStatement.setInt(2, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(3, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERID,USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER WHERE USERNAME = ? ");
				userPreparedStatement.setString(1, userName);
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIsActive(rs.getString(4));
				user.setCreatedBy(rs.getString(5));
				user.setCreatedDate(rs.getDate(6));
				user.setLastUpdatedBy(rs.getString(7));
				user.setLastUpdateDate(rs.getDate(8));
				user.setLastUpdatedLogin(rs.getString(7));
			}

		} catch (SQLException e) {
			LOGGER.info("Error occured while fetching user data",e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.info("Error occured while fetching user data closing prepared statement",e.getMessage());
			}
		}

		return user;
	}

	@Override
	public boolean createUser(User user, PasswordEncoder passwordEncoder) {		
		LOGGER.info("Inside getSingleUserDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");  
		try (Connection connection = dataSource.getConnection()) {
			userPreparedStatement = connection.prepareStatement(
				"INSERT INTO USER(USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?, ?, ?, ?,?, ?, ?, ?)");
				userPreparedStatement.setString(1, user.getUserName());
				userPreparedStatement.setString(2, passwordEncoder.encode(user.getPassword()));
				userPreparedStatement.setString(3, user.getIsActive());
				userPreparedStatement.setString(4, user.getCreatedBy());
				userPreparedStatement.setString(5, sdf.format(new Date()));
				userPreparedStatement.setString(6, user.getUserName());
				userPreparedStatement.setString(7, sdf.format(new Date()));
				userPreparedStatement.setString(8, user.getUserName());
			int status = userPreparedStatement.executeUpdate();
			if(status > 0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.info("Error occured while fetching user data",e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.info("Error occured while fetching user data closing prepared statement",e.getMessage());
			}
		}
		return false;
	}

}
