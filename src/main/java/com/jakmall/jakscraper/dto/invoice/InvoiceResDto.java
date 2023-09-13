package com.jakmall.jakscraper.dto.invoice;

import java.util.List;

import com.jakmall.jakscraper.dto.invoicedetail.InvoiceDetailResDto;

public class InvoiceResDto {

	private Long id;
	private String invoiceCode;
	private Long grandTotal;
	private List<InvoiceDetailResDto> detail;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setDetail(List<InvoiceDetailResDto> detail) {
		this.detail = detail;
	}
	public List<InvoiceDetailResDto> getDetail() {
		return detail;
	}
	
}
