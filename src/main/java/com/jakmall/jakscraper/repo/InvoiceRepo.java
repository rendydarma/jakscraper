package com.jakmall.jakscraper.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long>{

	List<Invoice> getByStore(Long storeId);
}
