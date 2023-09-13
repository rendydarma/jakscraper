package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.InvoiceDetail;
import com.jakmall.jakscraper.repo.InvoiceDetailRepo;

@Repository
public class InvoiceDetailDao {

	@Autowired
	private InvoiceDetailRepo invoiceDetailRepo;
	
	public InvoiceDetail insert(InvoiceDetail invoiceDetail) {
		invoiceDetailRepo.save(invoiceDetail);
		return invoiceDetail;
	}
	
	public List<InvoiceDetail> getDetail(Long invoiceId){
		final List<InvoiceDetail> details = invoiceDetailRepo.getByInvoice(invoiceId);
		return details;
	}
}
