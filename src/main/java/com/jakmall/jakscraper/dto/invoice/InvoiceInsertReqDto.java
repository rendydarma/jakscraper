package com.jakmall.jakscraper.dto.invoice;

import java.util.List;

import com.jakmall.jakscraper.dto.invoicedetail.InvoiceDetailInsertReqDto;

public class InvoiceInsertReqDto {
	
	private String invoiceCode;
	private Long storeId;
	private String buyerName;
	private String buyerAddress;
	private Long grandTotal;
	private List<InvoiceDetailInsertReqDto> details;
	
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
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
	public List<InvoiceDetailInsertReqDto> getDetails() {
		return details;
	}
	public void setDetails(List<InvoiceDetailInsertReqDto> details) {
		this.details = details;
	}
}
