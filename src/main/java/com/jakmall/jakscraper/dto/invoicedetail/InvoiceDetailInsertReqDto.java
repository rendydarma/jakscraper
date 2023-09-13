package com.jakmall.jakscraper.dto.invoicedetail;

public class InvoiceDetailInsertReqDto {

	private String sku;
	private Long qty;
	
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
}
