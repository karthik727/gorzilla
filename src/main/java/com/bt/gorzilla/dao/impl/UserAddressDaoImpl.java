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
import com.bt.gorzilla.dao.UserAddressDao;
import com.bt.gorzilla.entity.UserAddress;

//Spring data jpa has vulnerabilities using native daoimpl
//https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.1.5
@Repository
public class UserAddressDaoImpl implements UserAddressDao {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<UserAddress> getUserAddressDetails(PaginationBean paginationBean) {
		LOGGER.info("Iside getUserAddressDetails DaoImpl");
		PreparedStatement userPreparedStatement = null;
		UserAddress userAddress;
		List<UserAddress> userAddressList = new LinkedList<UserAddress>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != paginationBean) {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER_ADDRESS.USERADDRESSID, USER_ADDRESS.USERID, USER_ADDRESS.ADDRESS, USER_ADDRESS.CITY, USER_ADDRESS.PINCODE, USER_ADDRESS.COUNTRY, USER_ADDRESS.CREATEDBY, USER_ADDRESS.CREATEDDATE, USER_ADDRESS.LASTUPDATEDBY, USER_ADDRESS.LASTUPDATEDDATE, USER_ADDRESS.LASTUPDATEDLOGIN FROM USER_ADDRESS LIMIT ? OFFSET ?");
				userPreparedStatement.setInt(1, paginationBean.getNumberOfRows());
				userPreparedStatement.setInt(2, paginationBean.getRowsToSkip());
			} else {
				userPreparedStatement = connection.prepareStatement(
						"SELECT USER_ADDRESS.USERADDRESSID, USER_ADDRESS.USERID, USER_ADDRESS.ADDRESS, USER_ADDRESS.CITY, USER_ADDRESS.PINCODE, USER_ADDRESS.COUNTRY, USER_ADDRESS.CREATEDBY, USER_ADDRESS.CREATEDDATE, USER_ADDRESS.LASTUPDATEDBY, USER_ADDRESS.LASTUPDATEDDATE, USER_ADDRESS.LASTUPDATEDLOGIN FROM USER_ADDRESS");
			}
			ResultSet rs = userPreparedStatement.executeQuery();
			while (rs.next()) {
				userAddress = new UserAddress();
				userAddress.setUserAddressId(rs.getInt(1));
				userAddress.setUserId(rs.getInt(2));
				userAddress.setAddress(rs.getString(3));
				userAddress.setCity(rs.getString(4));
				userAddress.setPincode(rs.getString(5));
				userAddress.setCountry(rs.getString(6));
				userAddress.setCreatedBy(rs.getString(7));
				userAddress.setCreatedDate(rs.getDate(8));
				userAddress.setLastUpdatedBy(rs.getString(9));
				userAddress.setLastUpdateDate(rs.getDate(10));
				userAddress.setLastUpdatedLogin(rs.getString(11));
				userAddressList.add(userAddress);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching user address data", e.getMessage());
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user address data closing prepared statement", e.getMessage());
			}
		}

		return userAddressList;
	}

}
