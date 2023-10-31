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
import com.bt.gorzilla.dao.UserRoleDao;
import com.bt.gorzilla.entity.UserRole;

//Spring data jpa has vulnerabilities using native daoimpl
//https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.1.5
@Repository
public class UserRoleDaoImpl implements UserRoleDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<UserRole> getUserRoles(PaginationBean paginationBean) {
		LOGGER.info("Iside getUserRoles DaoImpl");
		PreparedStatement userPreparedStatement = null;
		UserRole userRole;
		List<UserRole> userRoleList = new LinkedList<UserRole>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERROLEID,USERID,ROLEID,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER_ROLE LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USERROLEID,USERID,ROLEID,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM USER_ROLE");
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				userRole = new UserRole();
				userRole.setUserRoleId(rs.getInt(1));
				userRole.setUserId(rs.getInt(2));
				userRole.setRoleId(rs.getInt(3));
				userRole.setCreatedBy(rs.getString(4));
				userRole.setCreatedDate(rs.getDate(5));
				userRole.setLastUpdatedBy(rs.getString(6));
				userRole.setLastUpdateDate(rs.getDate(7));
				userRole.setLastUpdatedLogin(rs.getString(8));
				userRoleList.add(userRole);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching user role data", e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user role data closing prepared statement", e.getMessage());
			}
		}

		return userRoleList;
	}

}
