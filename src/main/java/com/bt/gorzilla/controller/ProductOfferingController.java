package com.bt.gorzilla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.FailureBean;
import com.bt.gorzilla.bean.ProductOfferingBean;
import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.constant.GorzillaErrorConstant;
import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.exception.ProductOfferingException;
import com.bt.gorzilla.service.ProductOfferingService;
import com.bt.gorzilla.util.GorzillaUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "8. Product Catalog TMF620")
public class ProductOfferingController {
	
	@Autowired
	ProductOfferingService productOfferingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductOfferingController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_OFFERING, method = RequestMethod.GET)
	public ResponseEntity<List<ProductOffering>> getAllProducts(Authentication authentication) {
		LOGGER.info("Inside getAllProducts");
		String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
		LOGGER.info("loggedInUserName:"+loggedInUserName);
		List<ProductOffering> productOfferingList = productOfferingService.getAllProducts();
		return ResponseEntity.ok().body(productOfferingList);
	}
	
	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.PRODUCT_OFFERING, method = RequestMethod.POST)
	public ResponseEntity<Object> createProductCatalog(Authentication authentication,@RequestBody ProductOfferingBean ProductOfferingBean) {
		LOGGER.info("Inside createProductCatalog");
		try {
			String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
			LOGGER.info("loggedInUserName:"+loggedInUserName);
			productOfferingService.createProduct(ProductOfferingBean,loggedInUserName);
			SuccessBean se = new SuccessBean();
			se.setSuccessMessage("Product created successfully");
			se.setSuccessCode("200");
			return ResponseEntity.ok().body(se);
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
