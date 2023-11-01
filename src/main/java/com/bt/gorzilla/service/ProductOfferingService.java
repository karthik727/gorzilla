package com.bt.gorzilla.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.bean.ProductOfferingBean;
import com.bt.gorzilla.dao.ProductOfferingDao;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.exception.ProductOfferingException;
import com.bt.gorzilla.repository.ProductOfferingRepository;

@Service
public class ProductOfferingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductOfferingService.class);
	
	@Autowired
	ProductOfferingRepository productOfferingRepository;

	@Autowired
	ProductOfferingDao productOfferingDao;

	public List<ProductOffering> getAllProducts() {
		LOGGER.info("Inside getAllProducts service");
		List<ProductOffering> productOfferingList = new LinkedList<ProductOffering>();
		Iterable<ProductOffering> productOffering = productOfferingRepository.findAll();
		Iterator<ProductOffering> productOfferingIterator = productOffering.iterator();
		while(productOfferingIterator.hasNext()) {
			productOfferingList.add(productOfferingIterator.next());
		}
		return productOfferingList;
	}


	public void createProduct(ProductOfferingBean productOfferingBean, String loggedInUserName) throws ProductOfferingException {
		LOGGER.info("Inside createProduct service");
		productOfferingDao.createProduct(productOfferingBean,loggedInUserName);
	}

}
