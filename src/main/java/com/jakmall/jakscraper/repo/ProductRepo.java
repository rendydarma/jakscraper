package com.jakmall.jakscraper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Product getBySku(String sku);
}
