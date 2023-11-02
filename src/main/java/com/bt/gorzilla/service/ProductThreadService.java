package com.bt.gorzilla.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.dao.ProductOfferingDao;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.exception.ProductOfferingException;
import com.bt.gorzilla.thread.ProductCatalogThread;

@Service
public class ProductThreadService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductThreadService.class);
	
	@Autowired
	ProductOfferingDao productOfferingDao;


	public List<ProductOffering> getProductUsingThreads() throws ProductOfferingException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
	    CountDownLatch productCountDownLatch = new CountDownLatch(2);
	    Integer totalProductCatalogCount = productOfferingDao.getTotalProductCatalogCount();
	    LOGGER.info("Total product count:"+totalProductCatalogCount);
	    Integer midPosition = calculateMidPosition(totalProductCatalogCount);
	    LOGGER.info("midPosition product count:"+midPosition);
	    Future<List<ProductOffering> > firstThreadFuture = null;
	    Future<List<ProductOffering>> secondThreadFuture = null;
	    if(null!=midPosition) {
	    	productCountDownLatch = new CountDownLatch(2);
		    ProductCatalogThread productCatalogFirstThread = new ProductCatalogThread(0,midPosition,productOfferingDao,productCountDownLatch);
		    firstThreadFuture = executorService.submit(productCatalogFirstThread);
		    ProductCatalogThread productCatalogSecondThread = new ProductCatalogThread(midPosition,totalProductCatalogCount,productOfferingDao,productCountDownLatch);
		    secondThreadFuture = executorService.submit(productCatalogSecondThread);
	    }else {
	    	//No need of additional threads if count is less than 10
	    	productCountDownLatch = new CountDownLatch(1);
		    ProductCatalogThread productCatalogFirstThread = new ProductCatalogThread(0,totalProductCatalogCount,productOfferingDao,productCountDownLatch);
		    firstThreadFuture = executorService.submit(productCatalogFirstThread);
	    }
	    productCountDownLatch.await(60, TimeUnit.SECONDS);
	    List<ProductOffering> completeProductOffering = combineTwoListsUsingStream(firstThreadFuture,secondThreadFuture);
	    return completeProductOffering;
	}

	private List<ProductOffering> combineTwoListsUsingStream(Future<List<ProductOffering>> firstThreadFuture, Future<List<ProductOffering>> secondThreadFuture) {
		List<ProductOffering> completeProductList = null;
		List<ProductOffering> firstList = null;
		List<ProductOffering> secondList = null;
		firstList = getListOfProduct(firstThreadFuture);
		secondList = getListOfProduct(secondThreadFuture);
		if(null!=firstList && !firstList.isEmpty() && null!=secondList && !secondList.isEmpty()) {
			completeProductList = Stream.concat(firstList.stream(), secondList.stream()).toList();
			return completeProductList;
		}
		else if (null==firstList || firstList.isEmpty()) {
			return secondList;
		}
		else if (null==secondList || secondList.isEmpty()) {
			return firstList;
		}
		return completeProductList;
	}

	private List<ProductOffering> getListOfProduct(Future<List<ProductOffering>> threadFuture) {
		List<ProductOffering> productList = null;
		if(null!= threadFuture && threadFuture.isDone()) {
			try {
				return threadFuture.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	private Integer calculateMidPosition(Integer totalProductCatalogCount) {
		Integer midPosition = null;
		if(null!= totalProductCatalogCount && totalProductCatalogCount >10) {
			return totalProductCatalogCount/2; 
		}
		return midPosition;
	}

}
