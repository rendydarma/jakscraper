package com.jakmall.jakscraper.dto.soldproduct;

public class SoldProductInsertReqDto {
	private String sku;
	private Long qty;
	private Long grandTotal;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Long getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}
}
