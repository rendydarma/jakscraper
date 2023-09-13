package com.jakmall.jakscraper.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.InvoiceDetail;

@Repository
public interface InvoiceDetailRepo extends JpaRepository<InvoiceDetail, Long>{

	List<InvoiceDetail> getByInvoice(Long invoiceId);
}
