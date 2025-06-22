package com.jakmall.jakscraper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.SoldProduct;

@Repository
public interface SoldProductRepo extends JpaRepository<SoldProduct, Long>{

	SoldProduct getByProductSku(String sku);
}
