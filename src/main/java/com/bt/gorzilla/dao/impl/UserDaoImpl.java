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
import com.bt.gorzilla.dao.UserDao;
import com.bt.gorzilla.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<User> getUserDetails(PaginationBean paginationBean) {
		LOGGER.info("Iside getUserDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		User user;
		List<User> userList = new LinkedList<User>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERID,USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERID,USERNAME,PASSWORD,ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER");
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
		LOGGER.info("Iside getSingleUserDetails DaoImpl");
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

}
