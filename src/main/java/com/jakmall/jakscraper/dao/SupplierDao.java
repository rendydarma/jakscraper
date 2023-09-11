package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Supplier;
import com.jakmall.jakscraper.repo.SupplierRepo;

@Repository
public class SupplierDao {

	@Autowired
	private SupplierRepo supplierRepo;
	
	public List<Supplier> getSuppliers(){
		final List<Supplier> suppliers = supplierRepo.findAll();
		return suppliers;
	}
	
	public Supplier insert(Supplier supplier) {
		supplierRepo.save(supplier);
		return supplier;
	}
	
}
