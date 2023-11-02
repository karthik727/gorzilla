package com.bt.gorzilla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.FailureBean;
import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.constant.GorzillaErrorConstant;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.exception.ProductOfferingException;
import com.bt.gorzilla.service.ProductThreadService;
import com.bt.gorzilla.util.GorzillaUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "9c. Product Catalog Threads with Executor and CoundDownLatch")
public class ProductThreadController {
	
	@Autowired
	ProductThreadService productThreadService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductThreadController.class);
	
	/*
	 * getProductUsingThreads method gets created threads using ExecutorService and CountDownLatch and brings
	 * Product Catalog data. This type of implementation is helpful when we are exporting huge data to file
	 */

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_STREAM + GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_THREAD, method = RequestMethod.GET)
	public ResponseEntity<Object> getProductUsingThreads(Authentication authentication) {
	try {
		LOGGER.info("Inside getProductUsingThreads");
		String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
		LOGGER.info("loggedInUserName:"+loggedInUserName);
		List<ProductOffering> productOfferingList = productThreadService.getProductUsingThreads();
		return ResponseEntity.ok().body(productOfferingList);
	}catch(ProductOfferingException e) {
		LOGGER.error("Error:"+e.getMessage());
		FailureBean fe = new FailureBean();
		fe.setErrorCode("400");
		fe.setErrorMessage(GorzillaErrorConstant.GE_0006);
		return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
	} catch (Exception e) {
		LOGGER.error("Error:" + e.getMessage());
		FailureBean fe = new FailureBean();
		fe.setErrorCode("500");
		fe.setErrorMessage(GorzillaErrorConstant.GE_0001);
		return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
	}
  }

}
