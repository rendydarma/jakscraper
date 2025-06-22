package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Invoice;
import com.jakmall.jakscraper.repo.InvoiceRepo;

@Repository
public class InvoiceDao {

	@Autowired
	private InvoiceRepo invoiceRepo;
	
	public Invoice insert(Invoice invoice) {
		invoice = invoiceRepo.save(invoice);
		return invoice;
	}
	
	public List<Invoice> getAllByStore(Long storeId) {
		final List<Invoice> invoices = invoiceRepo.getByStore(storeId);
		return invoices;
	}
	
	public List<Invoice> getAll() {
		final List<Invoice> invoices = invoiceRepo.findAll();
		return invoices;
	}
}
