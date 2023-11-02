package com.bt.gorzilla.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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

import com.bt.gorzilla.bean.ProductOfferingBean;
import com.bt.gorzilla.bean.ProductOfferingPriceBean;
import com.bt.gorzilla.bean.ProductSpecificationRefBean;
import com.bt.gorzilla.dao.ProductOfferingDao;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.entity.UserAddress;
import com.bt.gorzilla.entity.UserInfo;
import com.bt.gorzilla.entity.UserRole;
import com.bt.gorzilla.exception.ProductOfferingException;
import com.bt.gorzilla.exception.UserRegistrationException;

@Repository
public class ProductOfferingDaoImpl implements ProductOfferingDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductOfferingDaoImpl.class);
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void createProduct(ProductOfferingBean productOfferingBean, String loggedInUserName) throws ProductOfferingException {
		LOGGER.info("Inside createProduct DaoImpl");	
		PreparedStatement userPreparedStatement = null;
		Connection connection = null;
		try {		
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
	        java.util.Date javaDate = new java.util.Date();
	        java.sql.Date date = new java.sql.Date(javaDate.getTime());
	        createProductOffering(connection,productOfferingBean,loggedInUserName,date);
	        Integer productId = getProductId(connection,productOfferingBean.getName());
	        if(null!=productOfferingBean.getProductOfferingPrice()) {
				createProductOfferingPrice(connection,productOfferingBean.getProductOfferingPrice(),productId,loggedInUserName,date);
	        }
	        if(null!=productOfferingBean.getProductSpecificationRef()) {
				createProductSpecificationRef(connection,productOfferingBean.getProductSpecificationRef(),productId,loggedInUserName,date);
	        }
			connection.commit();
		} catch (Exception e) {
			LOGGER.error("Error occured while inserting product data",e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			e.printStackTrace();
			throw new ProductOfferingException("Error while storing product data");
		} finally {
			try {
				if (null != connection && !connection.isClosed()) {
					connection.close();
				}
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching product data closing prepared statement",e.getMessage());
			}
		}
	}

	private void createProductOffering(Connection connection, ProductOfferingBean productOfferingBean,String loggedInUserName, Date date) throws ProductOfferingException {
		PreparedStatement userPreparedStatement = null;
	    try {
			userPreparedStatement = connection.prepareStatement(
				"INSERT INTO PRODUCTOFFERING (NAME, HREF, DESCRIPTION,ISBUNDLE,LASTUPDATE,LIFECYCLESTATUS,VALIDFOR,VERSION,TYPE,BASETYPE,SCHEMALOCATION,ISSELLABLE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				userPreparedStatement.setString(1, productOfferingBean.getName());
				userPreparedStatement.setString(2, productOfferingBean.getHref());
				userPreparedStatement.setString(3, productOfferingBean.getDescription());
				userPreparedStatement.setString(4, productOfferingBean.getIsBundle());
				userPreparedStatement.setDate(5, date);
				userPreparedStatement.setString(6, productOfferingBean.getLifeCycleStatus());
				userPreparedStatement.setString(7, productOfferingBean.getValidFor());
				userPreparedStatement.setString(8, productOfferingBean.getVersion());
				userPreparedStatement.setString(9, productOfferingBean.getType());
				userPreparedStatement.setString(10, productOfferingBean.getBaseType());
				userPreparedStatement.setString(11, productOfferingBean.getSchemaLocation());
				userPreparedStatement.setString(12, productOfferingBean.getIsSellable());
				userPreparedStatement.setString(13, loggedInUserName);
				userPreparedStatement.setDate(14, date);
				userPreparedStatement.setString(15, loggedInUserName);
				userPreparedStatement.setDate(16, date);
				userPreparedStatement.setString(17, loggedInUserName);
			int status = userPreparedStatement.executeUpdate();
			if(status == 0) {
				throw new ProductOfferingException("Error while storing product data");
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error occured while saving product data",e.getMessage());
			e.printStackTrace();
			throw new ProductOfferingException("Error while storing product data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing product data closing prepared statement",e.getMessage());
			}
		}
	}
	

	private Integer getProductId(Connection connection, String name) throws ProductOfferingException {
		PreparedStatement userPreparedStatement = null;
		Integer productId = null;
		try {
			userPreparedStatement = connection.prepareStatement(
				"SELECT PRODUCTOFFERINGID FROM PRODUCTOFFERING WHERE NAME = ?");
				userPreparedStatement.setString(1, name);
				ResultSet rs = userPreparedStatement.executeQuery();
				if(rs == null) {
					throw new ProductOfferingException("Error while fetching productId from name");
				}
				while (rs.next()) {
					return rs.getInt(1);
				}
		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching productId data",e.getMessage());
			e.printStackTrace();
			throw new ProductOfferingException("Error while fetching productId data");
		} finally {
			try {
				if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
					userPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching user productId closing prepared statement",e.getMessage());
			}
		}
		return productId;
	}
	
	
	private void createProductOfferingPrice(Connection connection, List<ProductOfferingPriceBean> productOfferingPriceBeanList, Integer productId, String loggedInUserName, Date date) throws ProductOfferingException {
		PreparedStatement userPreparedStatement = null;
		for(ProductOfferingPriceBean productOfferingPriceBean:productOfferingPriceBeanList) {
			try{
				userPreparedStatement = connection.prepareStatement(
					"INSERT INTO PRODUCTOFFERINGPRICE (PRODUCTOFFERINGID,NAME,DESCRIPTION,VALIDFOR,PRICETYPE,UNITOFMEASURE,RECURRINGCHARGEPRICE,VERSION, HREF, TYPE,BASETYPE,SCHEMALOCATION,ISBUNDLE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					userPreparedStatement.setInt(1, productId);
					userPreparedStatement.setString(2, productOfferingPriceBean.getName());
					userPreparedStatement.setString(3, productOfferingPriceBean.getDescription());
					userPreparedStatement.setString(4, productOfferingPriceBean.getValidFor());
					userPreparedStatement.setString(5, productOfferingPriceBean.getPriceType());
					userPreparedStatement.setString(6, productOfferingPriceBean.getUnitOfMeasure());
					userPreparedStatement.setString(7, productOfferingPriceBean.getRecurringChargePrice());
					userPreparedStatement.setString(8, productOfferingPriceBean.getVersion());
					userPreparedStatement.setString(9, productOfferingPriceBean.getHref());
					userPreparedStatement.setString(10, productOfferingPriceBean.getType());
					userPreparedStatement.setString(11, productOfferingPriceBean.getBaseType());
					userPreparedStatement.setString(12, productOfferingPriceBean.getSchemaLocation());
					userPreparedStatement.setString(13, productOfferingPriceBean.getIsBundle());
					userPreparedStatement.setString(14, loggedInUserName);
					userPreparedStatement.setDate(15, date);
					userPreparedStatement.setString(16, loggedInUserName);
					userPreparedStatement.setDate(17, date);
					userPreparedStatement.setString(18, loggedInUserName);
				int status = userPreparedStatement.executeUpdate();
				if(status == 0) {
					throw new ProductOfferingException("Error while storing productOfferingPrice info data");
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing user info data",e.getMessage());
				e.printStackTrace();
				throw new ProductOfferingException("Error while storing productOfferingPrice info data");
			} finally {
				try {
					if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
						userPreparedStatement.close();
					}
				} catch (SQLException e) {
					LOGGER.error("Error occured while storing productOfferingPrice info data closing prepared statement",e.getMessage());
				}
			}
		}
	}
	
	private void createProductSpecificationRef(Connection connection,List<ProductSpecificationRefBean> productSpecificationRefList, Integer productId, String loggedInUserName, Date date) throws ProductOfferingException {
		PreparedStatement userPreparedStatement = null;
		for(ProductSpecificationRefBean productSpecificationRefBean:productSpecificationRefList) {
			try{
				userPreparedStatement = connection.prepareStatement(
					"INSERT INTO PRODUCTSPECIFICATIONREF (PRODUCTOFFERINGID,NAME,VERSION,HREF,REFERREDTYPE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN) VALUES (?,?,?,?,?,?,?,?,?,?)");
					userPreparedStatement.setInt(1, productId);
					userPreparedStatement.setString(2, productSpecificationRefBean.getName());
					userPreparedStatement.setString(3, productSpecificationRefBean.getVersion());
					userPreparedStatement.setString(4, productSpecificationRefBean.getHref());
					userPreparedStatement.setString(5, productSpecificationRefBean.getReferredType());
					userPreparedStatement.setString(6, loggedInUserName);
					userPreparedStatement.setDate(7, date);
					userPreparedStatement.setString(8, loggedInUserName);
					userPreparedStatement.setDate(9, date);
					userPreparedStatement.setString(10, loggedInUserName);
				int status = userPreparedStatement.executeUpdate();
				if(status == 0) {
					throw new ProductOfferingException("Error while storing productSpecificationRefBean info data");
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while storing user info data",e.getMessage());
				e.printStackTrace();
				throw new ProductOfferingException("Error while storing productSpecificationRefBean info data");
			} finally {
				try {
					if (null != userPreparedStatement && !userPreparedStatement.isClosed()) {
						userPreparedStatement.close();
					}
				} catch (SQLException e) {
					LOGGER.error("Error occured while storing productSpecificationRefBean info data closing prepared statement",e.getMessage());
				}
			}
		}
	}

	@Override
	public Integer getTotalProductCatalogCount() throws ProductOfferingException {
		LOGGER.info("Iside getTotalProductCatalogCount DaoImpl");
		PreparedStatement productCountPreparedStatement = null;
		Integer totalProductCatalogCount = null;
		try (Connection connection = dataSource.getConnection()) {
			productCountPreparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM PRODUCTOFFERING");
			ResultSet rs = productCountPreparedStatement.executeQuery();
			if (rs == null) {
				throw new ProductOfferingException("Error while fetching product count");
			}
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching product count data", e.getMessage());
		} finally {
			try {
				if (null != productCountPreparedStatement && !productCountPreparedStatement.isClosed()) {
					productCountPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching product count data closing prepared statement", e.getMessage());
			}
		}
		return totalProductCatalogCount;
	}

	@Override
	public List<ProductOffering> getProductCatalogData(Integer startPosition, Integer endPosition) {
		LOGGER.info("Iside getProductCatalogData DaoImpl");
		PreparedStatement productPreparedStatement = null;
		ProductOffering productOffering;
		List<ProductOffering> productOfferingList = new LinkedList<ProductOffering>();
		try (Connection connection = dataSource.getConnection()) {
			if (null != startPosition && null!= endPosition) {
				productPreparedStatement = connection.prepareStatement(
						"SELECT PRODUCTOFFERINGID, NAME, HREF, DESCRIPTION, ISBUNDLE, LASTUPDATE, LIFECYCLESTATUS, VALIDFOR, VERSION, TYPE, BASETYPE, SCHEMALOCATION, ISSELLABLE FROM PRODUCTOFFERING LIMIT ? OFFSET ?");
				productPreparedStatement.setInt(1,endPosition );
				productPreparedStatement.setInt(2,startPosition);
			} else {
				productPreparedStatement = connection.prepareStatement(
						"SELECT PRODUCTOFFERINGID, NAME, HREF, DESCRIPTION, ISBUNDLE, LASTUPDATE, LIFECYCLESTATUS, VALIDFOR, VERSION, TYPE, BASETYPE, SCHEMALOCATION, ISSELLABLE FROM PRODUCTOFFERING");
			}
			ResultSet rs = productPreparedStatement.executeQuery();
			while (rs.next()) {
				productOffering = new ProductOffering();
				productOffering.setProductOfferingId(rs.getInt(1));
				productOffering.setName(rs.getString(2));
				productOffering.setHref(rs.getString(3));
				productOffering.setDescription(rs.getString(4));
				productOffering.setIsBundle(rs.getString(5));
				productOffering.setLastUpdate(rs.getDate(6));
				productOffering.setLifeCycleStatus(rs.getString(7));
				productOffering.setValidFor(rs.getString(8));
				productOffering.setVersion(rs.getString(9));
				productOffering.setType(rs.getString(10));
				productOffering.setBaseType(rs.getString(11));
				productOffering.setSchemaLocation(rs.getString(12));
				productOffering.setIsSellable(rs.getString(13));
				productOfferingList.add(productOffering);
			}

		} catch (SQLException e) {
			LOGGER.error("Error occured while fetching ProductCatalogData", e.getMessage());
		} finally {
			try {
				if (null != productPreparedStatement && !productPreparedStatement.isClosed()) {
					productPreparedStatement.close();
				}
			} catch (SQLException e) {
				LOGGER.error("Error occured while fetching ProductCatalogData closing prepared statement", e.getMessage());
			}
		}
		return productOfferingList;
	}
}
