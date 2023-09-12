package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.ProductDao;
import com.jakmall.jakscraper.dto.product.ProductResDto;
import com.jakmall.jakscraper.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional
	public void insertProduct(List<Product> products) {
		for (Product p : products) {
			productDao.insert(p);
		}
	}

	public List<ProductResDto> getAll() {
		final List<ProductResDto> responses = new ArrayList<>();
		final List<Product> products = productDao.getAll();
		for (Product p : products) {
			final ProductResDto response = new ProductResDto();
			response.setSupplierName(p.getSupplier().getSupplierName());
			response.setSku(p.getSku());
			response.setPrice(p.getPrice());
			response.setInStock(p.getInStock());
			responses.add(response);
		}
		return responses;
	}

	public ProductResDto getProduct(String sku) {
		final Product product = productDao.getProduct(sku);
		final ProductResDto response = new ProductResDto();
		if(product != null) {			
			response.setSku(product.getSku());
			response.setPrice(product.getPrice());
			response.setInStock(product.getInStock());
		}

		return response;
	}
}
