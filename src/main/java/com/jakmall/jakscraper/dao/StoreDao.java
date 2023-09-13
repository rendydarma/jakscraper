package com.jakmall.jakscraper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jakmall.jakscraper.model.Store;
import com.jakmall.jakscraper.repo.StoreRepo;

@Repository
public class StoreDao {

	@Autowired
	private StoreRepo storeRepo;
	
	public Store insert(Store store) {
		storeRepo.save(store);
		return store;
	}
	
	public List<Store> getAll(){
		final List<Store> stores = storeRepo.findAll();
		return stores;
	}
}
