package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.StoreDao;
import com.jakmall.jakscraper.dto.InsertResDto;
import com.jakmall.jakscraper.dto.store.StoreInsertReqDto;
import com.jakmall.jakscraper.dto.store.StoreResDto;
import com.jakmall.jakscraper.model.Store;

@Service
public class StoreService {

	@Autowired
	private StoreDao storeDao;
	
	public List<StoreResDto> getAll(){
		final List<StoreResDto> responses = new ArrayList<>();
		
		final List<Store> stores = storeDao.getAll();
		for(Store s : stores) {
			final StoreResDto response = new StoreResDto();
			response.setId(s.getId());
			response.setStoreName(s.getStoreName());
			response.setUrl(s.getUrl());
			responses.add(response);
		}
		
		return responses;
	}
	
	@Transactional
	public InsertResDto insert(StoreInsertReqDto data) {
		final Store store = new Store();
		store.setStoreName(data.getStoreName());
		store.setUrl(data.getUrl());
		store.setCreatedBy(0L);
		storeDao.insert(store);
		
		if(store.getId() != null) {
			final InsertResDto response = new InsertResDto();
			response.setId(store.getId());
			response.setMessage("Insert has been successfully");
			return response;
		}else {
			return null;
		}
		
	}
}
