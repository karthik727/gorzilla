package com.bt.gorzilla.service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.gorzilla.entity.ProductOffering;
import com.bt.gorzilla.entity.ProductOfferingPrice;
import com.bt.gorzilla.util.GorzillaUtil;

@Service
public class ProductStreamService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStreamService.class);
	
	@Autowired
	ProductOfferingService productOfferingService;

	public List<ProductOffering> getProductByIsBundleY() {
		LOGGER.info("Inside getProductByIsBundleY service");
		List<ProductOffering> productOfferingList = productOfferingService.getAllProducts();
		List<ProductOffering> productOfferingIsBundledList = getIsBundledByStream(productOfferingList);
		return productOfferingIsBundledList;
	}

	public List<ProductOffering> getIsBundledByStream(List<ProductOffering> productOfferingList) {
		Predicate<ProductOffering> isBundlePredicate = new Predicate<ProductOffering>() {
			@Override
			public boolean test(ProductOffering product) {
				if(product.getIsBundle().equals("Y")) {
					return true;
				}
				return false;
			}
		};
		return productOfferingList.stream().filter(isBundlePredicate).collect(Collectors.toList());
	}

	public List<ProductOffering> getProductBySchemaLocation() {
		LOGGER.info("Inside getProductBySchemaLocation service");
		List<ProductOffering> productOfferingList = productOfferingService.getAllProducts();
		List<ProductOffering> productOfferingBySchemaLocation = getProductBySchemaLocation(productOfferingList);
		return productOfferingBySchemaLocation;
	}

	private List<ProductOffering> getProductBySchemaLocation(List<ProductOffering> productOfferingList) {
		Function<ProductOffering,ProductOffering> productBySchemaLocationMap = new Function<ProductOffering,ProductOffering>(){
			@Override
			public ProductOffering apply(ProductOffering productOffering) {
				List<ProductOfferingPrice>  productOfferingPriceList = productOffering.getProductOfferingPrice();
				if(null!=productOffering) {
					if(!GorzillaUtil.isStringEmpty(productOffering.getSchemaLocation())&& productOffering.getSchemaLocation().equals("UK")) {
						productOffering.setSchemaLocation("United Kingdom");
					}
					for(ProductOfferingPrice productOfferingPrice : productOfferingPriceList) {
						if(null != productOfferingPrice && !GorzillaUtil.isStringEmpty(productOfferingPrice.getSchemaLocation()) 
								&& productOfferingPrice.getSchemaLocation().equals("UK")) {
							productOfferingPrice.setSchemaLocation("United Kingdom");
						}
					}
				}
				return productOffering;
			}
		};
		return productOfferingList.stream().map(productBySchemaLocationMap).collect(Collectors.toList());
	}

}
