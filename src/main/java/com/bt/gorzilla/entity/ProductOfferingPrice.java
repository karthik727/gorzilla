package com.bt.gorzilla.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTOFFERINGPRICE")
public class ProductOfferingPrice extends Audit {

	@Id
	private Integer productOfferingPriceId;
	private Integer productOfferingId;
	private String name;
	private String description;
	private String validFor;
	private String priceType;
	private String unitOfMeasure;
	private String recurringChargePrice;
	private String version;
	private String href;
	private String type;
	private String baseType;
	private String schemaLocation;
	private String isBundle;
	
	
	public Integer getProductOfferingPriceId() {
		return productOfferingPriceId;
	}
	public void setProductOfferingPriceId(Integer productOfferingPriceId) {
		this.productOfferingPriceId = productOfferingPriceId;
	}
	public Integer getProductOfferingId() {
		return productOfferingId;
	}
	public void setProductOfferingId(Integer productOfferingId) {
		this.productOfferingId = productOfferingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public String getRecurringChargePrice() {
		return recurringChargePrice;
	}
	public void setRecurringChargePrice(String recurringChargePrice) {
		this.recurringChargePrice = recurringChargePrice;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
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
	public String getIsBundle() {
		return isBundle;
	}
	public void setIsBundle(String isBundle) {
		this.isBundle = isBundle;
	}
	@Override
	public String toString() {
		return "ProductOfferingPrice [productOfferingPriceId=" + productOfferingPriceId + ", productOfferingId="
				+ productOfferingId + ", name=" + name + ", description=" + description + ", validFor=" + validFor
				+ ", priceType=" + priceType + ", unitOfMeasure=" + unitOfMeasure + ", recurringChargePrice="
				+ recurringChargePrice + ", version=" + version + ", href=" + href + ", type=" + type + ", baseType="
				+ baseType + ", schemaLocation=" + schemaLocation + ", isBundle=" + isBundle + "]";
	}
	
}
