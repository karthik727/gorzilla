package com.bt.gorzilla.dao;

import com.bt.gorzilla.bean.ProductOfferingBean;
import com.bt.gorzilla.exception.ProductOfferingException;

public interface ProductOfferingDao {

	void createProduct(ProductOfferingBean productOfferingBean, String loggedInUserName) throws ProductOfferingException;

}
