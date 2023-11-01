package com.bt.gorzilla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.service.ProductStreamService;
import com.bt.gorzilla.util.GorzillaUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "9a. Product Catalog Stream With Filter")
public class ProductStreamController {
	
	@Autowired
	ProductStreamService productStreamService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStreamController.class);
	
	/*
	 * getProductByIsBundleY method gets all the product catalog available and uses stream 
	 * filter functions to collect products which has isBundle='Y'
	 */

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_STREAM, method = RequestMethod.GET)
	public ResponseEntity<List<ProductOffering>> getProductByIsBundleY(Authentication authentication) {
		LOGGER.info("Inside getProductByIsBundleY");
		String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
		LOGGER.info("loggedInUserName:"+loggedInUserName);
		List<ProductOffering> productOfferingByIsBundle = productStreamService.getProductByIsBundleY();
		return ResponseEntity.ok().body(productOfferingByIsBundle);
	}

}
