package com.bt.gorzilla.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.bt.gorzilla.dao.ProductOfferingDao;
import com.bt.gorzilla.entity.ProductOffering;

public class ProductCatalogThread implements Callable<List<ProductOffering>>{
		
	Integer startPosition;
	Integer endPosition;
	CountDownLatch productCountDownLatch;
	ProductOfferingDao productOfferingDao;

	public ProductCatalogThread(Integer startPosition, Integer endPosition, ProductOfferingDao productOfferingDao, CountDownLatch productCountDownLatch) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.productOfferingDao = productOfferingDao;
		this.productCountDownLatch = productCountDownLatch;
	}

	@Override
	public List<ProductOffering> call() throws Exception {
		List<ProductOffering> productOfferingData = productOfferingDao.getProductCatalogData(startPosition,endPosition);
		productCountDownLatch.countDown();
		return productOfferingData;
	}
	
	

}
