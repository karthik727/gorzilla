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
@Tag(name = "9b. Product Catalog Stream With Map")
public class ProductStreamMapController {
	
	@Autowired
	ProductStreamService productStreamService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStreamMapController.class);
	
	/*
	 * getProductBySchemaLocation method gets all the product catalog available and uses stream 
	 * filter and Map functions to collect products which has schemaLocation='UK' by changing it to schemaLocation=United Kingdom
	 */

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_STREAM + GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_MAP, method = RequestMethod.GET)
	public ResponseEntity<List<ProductOffering>> getProductBySchemaLocation(Authentication authentication) {
		LOGGER.info("Inside getProductBySchemaLocation");
		String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
		LOGGER.info("loggedInUserName:"+loggedInUserName);
		List<ProductOffering> productOfferingBySchemaLocation = productStreamService.getProductBySchemaLocation();
		return ResponseEntity.ok().body(productOfferingBySchemaLocation);
	}


}
