package com.bt.gorzilla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.service.ProductOfferingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "7. Product Catalog")
public class ProductOfferingController {
	
	@Autowired
	ProductOfferingService productOfferingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductOfferingController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_OFFERING, method = RequestMethod.GET)
	public ResponseEntity<List<ProductOffering>> getAllProducts(@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getAllProducts");
		List<ProductOffering> productOfferingList = productOfferingService.getAllProducts(page, size);
		return ResponseEntity.ok().body(productOfferingList);
	}


}
