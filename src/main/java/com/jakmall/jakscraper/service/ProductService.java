package com.jakmall.jakscraper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.ProductDao;
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
}
