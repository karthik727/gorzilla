package com.bt.gorzilla.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTOFFERING")
@JsonInclude(Include.NON_NULL)
public class ProductOffering {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productOfferingId;
	private String name;
	private String href;
	private String description;
	private String isBundle;
	private Date lastUpdate;
	private String lifeCycleStatus;
	private String validFor;
	private String version;
	private String type;
	private String baseType;
	private String schemaLocation;
	private String isSellable;

	@OneToMany
	@JoinColumn(name = "productOfferingId")
	private List<ProductOfferingPrice> productOfferingPrice;

	@OneToMany
	@JoinColumn(name = "productOfferingId")
	private List<ProductSpecificationRef> productSpecificationRef;

	@Column(name = "PRODUCTOFFERINGID")
	public Integer getProductOfferingId() {
		return productOfferingId;
	}

	public void setProductOfferingId(Integer productOfferingId) {
		this.productOfferingId = productOfferingId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HREF")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ISBUNDLE")
	public String getIsBundle() {
		return isBundle;
	}

	public void setIsBundle(String isBundle) {
		this.isBundle = isBundle;
	}

	@Column(name = "LASTUPDATE")
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "LIFECYCLESTATUS")
	public String getLifeCycleStatus() {
		return lifeCycleStatus;
	}

	public void setLifeCycleStatus(String lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}

	@Column(name = "VALIDFOR")
	public String getValidFor() {
		return validFor;
	}

	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}

	@Column(name = "VERSION")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BASETYPE")
	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	@Column(name = "SCHEMALOCATION")
	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	@Column(name = "ISSELLABLE")
	public String getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(String isSellable) {
		this.isSellable = isSellable;
	}

	public List<ProductOfferingPrice> getProductOfferingPrice() {
		return productOfferingPrice;
	}

	public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
		this.productOfferingPrice = productOfferingPrice;
	}

	public List<ProductSpecificationRef> getProductSpecificationRef() {
		return productSpecificationRef;
	}

	public void setProductSpecificationRef(List<ProductSpecificationRef> productSpecificationRef) {
		this.productSpecificationRef = productSpecificationRef;
	}

	@Override
	public String toString() {
		return "ProductOffering [productOfferingId=" + productOfferingId + ", name=" + name + ", href=" + href
				+ ", description=" + description + ", isBundle=" + isBundle + ", lastUpdate=" + lastUpdate
				+ ", lifeCycleStatus=" + lifeCycleStatus + ", validFor=" + validFor + ", version=" + version + ", type="
				+ type + ", baseType=" + baseType + ", schemaLocation=" + schemaLocation + ", isSellable=" + isSellable
				+ ", productOfferingPrice=" + productOfferingPrice + ", productSpecificationRef="
				+ productSpecificationRef + "]";
	}

}
