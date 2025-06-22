package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.SoldProduct;
import com.jakmall.jakscraper.repo.SoldProductRepo;

@Repository
public class SoldProductDao {
	
	@Autowired
	private SoldProductRepo soldProductRepo;
	
	public SoldProduct insert(SoldProduct soldProduct) {
		soldProductRepo.save(soldProduct);
		return soldProduct;
	}
	
	public List<SoldProduct> getAll() {
		final List<SoldProduct> soldProducts = soldProductRepo.findAll();
		return soldProducts;
	}
	
	public SoldProduct getById(Long id) {
		final SoldProduct soldProduct = soldProductRepo.findById(id).get();
		return soldProduct;
	}
	
	public SoldProduct getBySku(String sku) {
		final SoldProduct soldProduct = soldProductRepo.getByProductSku(sku);
		return soldProduct;
	}
}
