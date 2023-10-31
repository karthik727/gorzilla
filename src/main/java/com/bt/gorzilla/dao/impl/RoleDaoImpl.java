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
import com.bt.gorzilla.dao.RoleDao;
import com.bt.gorzilla.entity.Role;

//Spring data jpa has vulnerabilities using native daoimpl
//https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.1.5
@Repository
public class RoleDaoImpl implements RoleDao {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Role> getAllRoles(PaginationBean paginationBean) {
		LOGGER.info("Iside getAllRoles DaoImpl");
		PreparedStatement userPreparedStatement = null;
		Role role;
		List<Role> roleList = new LinkedList<Role>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT ROLEID,ROLENAME,ROLEDESCRIPTION,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM ROLE LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT ROLEID,ROLENAME,ROLEDESCRIPTION,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN FROM ROLE");
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				role = new Role();
				role.setRoleId(rs.getInt(1));
				role.setRoleName(rs.getString(2));
				role.setRoleDescription(rs.getString(3));
				role.setCreatedBy(rs.getString(4));
				role.setCreatedDate(rs.getDate(5));
				role.setLastUpdatedBy(rs.getString(6));
				role.setLastUpdateDate(rs.getDate(7));
				role.setLastUpdatedLogin(rs.getString(8));
				roleList.add(role);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching role data", e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching role data closing prepared statement", e.getMessage());
			}
		}

		return roleList;
	}

}
