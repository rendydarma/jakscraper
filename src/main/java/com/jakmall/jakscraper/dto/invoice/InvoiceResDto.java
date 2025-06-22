package com.jakmall.jakscraper.dto.invoice;

import java.util.List;

import com.jakmall.jakscraper.dto.invoicedetail.InvoiceDetailResDto;

public class InvoiceResDto {

	private Long id;
	private String storeName;
	private String invoiceCode;
	private Long grandTotal;
	private List<InvoiceDetailResDto> details;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Long getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}
	public void setDetails(List<InvoiceDetailResDto> details) {
		this.details = details;
	}
	public List<InvoiceDetailResDto> getDetails() {
		return details;
	}
	
}
