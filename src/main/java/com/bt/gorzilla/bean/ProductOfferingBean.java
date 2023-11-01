package com.bt.gorzilla.bean;

import java.util.List;

public class ProductOfferingBean {

	private String name;
	private String href;
	private String description;
	private String isBundle;
	private String lifeCycleStatus;
	private String validFor;
	private String version;
	private String type;
	private String baseType;
	private String schemaLocation;
	private String isSellable;
	private List<ProductOfferingPriceBean> productOfferingPrice;
	private List<ProductSpecificationRefBean> productSpecificationRef;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsBundle() {
		return isBundle;
	}

	public void setIsBundle(String isBundle) {
		this.isBundle = isBundle;
	}

	public String getLifeCycleStatus() {
		return lifeCycleStatus;
	}

	public void setLifeCycleStatus(String lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}

	public String getValidFor() {
		return validFor;
	}

	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	public String getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(String isSellable) {
		this.isSellable = isSellable;
	}

	public List<ProductOfferingPriceBean> getProductOfferingPrice() {
		return productOfferingPrice;
	}

	public void setProductOfferingPrice(List<ProductOfferingPriceBean> productOfferingPrice) {
		this.productOfferingPrice = productOfferingPrice;
	}

	public List<ProductSpecificationRefBean> getProductSpecificationRef() {
		return productSpecificationRef;
	}

	public void setProductSpecificationRef(List<ProductSpecificationRefBean> productSpecificationRef) {
		this.productSpecificationRef = productSpecificationRef;
	}

}
