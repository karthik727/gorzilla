package com.bt.gorzilla.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTSPECIFICATIONREF")
public class ProductSpecificationRef extends Audit {

	@Id
	private Integer productSpecificationRefId;
	private Integer productOfferingId;
	private String name;
	private String version;
	private String href;
	private String referredType;

	public Integer getProductSpecificationRefId() {
		return productSpecificationRefId;
	}

	public void setProductSpecificationRefId(Integer productSpecificationRefId) {
		this.productSpecificationRefId = productSpecificationRefId;
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

	public String getReferredType() {
		return referredType;
	}

	public void setReferredType(String referredType) {
		this.referredType = referredType;
	}

	@Override
	public String toString() {
		return "ProductSpecificationRef [productSpecificationRefId=" + productSpecificationRefId
				+ ", productOfferingId=" + productOfferingId + ", name=" + name + ", version=" + version + ", href="
				+ href + ", referredType=" + referredType + "]";
	}
	
}
