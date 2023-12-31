package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Product;
import com.jakmall.jakscraper.repo.ProductRepo;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepo productRepo;
	
	public void insert(Product product) {
		Product productDb = productRepo.getBySku(product.getSku());
		if(productDb != null) {
			productDb.setPrice(product.getPrice());
			productDb.setInStock(product.getInStock());
		}else {		
			productRepo.save(product);
		}
	}
	
	public List<Product> getAll(){
		final List<Product> products = productRepo.findAll();
		return products;
	}
	
	public Product getProduct(String sku){
		final Product product = productRepo.getBySku(sku);
		return product;
	}
}
