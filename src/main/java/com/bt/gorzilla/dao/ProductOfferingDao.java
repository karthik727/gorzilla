package com.bt.gorzilla.dao;

import java.util.List;

import com.bt.gorzilla.bean.ProductOfferingBean;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.exception.ProductOfferingException;

public interface ProductOfferingDao {

	void createProduct(ProductOfferingBean productOfferingBean, String loggedInUserName) throws ProductOfferingException;

	Integer getTotalProductCatalogCount() throws ProductOfferingException;

	List<ProductOffering> getProductCatalogData(Integer startPosition, Integer endPosition);

}
