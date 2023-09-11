package com.jakmall.jakscraper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_supplier")
public class Supplier extends BaseModel{

	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "url")
	private String url;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
