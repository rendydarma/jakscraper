package com.jakmall.jakscraper.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jakmall.jakscraper.model.Store;

public interface StoreRepo extends JpaRepository<Store, Long>{
	
}
