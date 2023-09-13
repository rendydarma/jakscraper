package com.jakmall.jakscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakmall.jakscraper.dto.InsertResDto;
import com.jakmall.jakscraper.dto.store.StoreInsertReqDto;
import com.jakmall.jakscraper.dto.store.StoreResDto;
import com.jakmall.jakscraper.service.StoreService;

@RestController
@RequestMapping("/stores")
public class StoreController {

	@Autowired
	private StoreService storeService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody StoreInsertReqDto data){
		final InsertResDto response = storeService.insert(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<StoreResDto>> getAll(){
		final List<StoreResDto> responses = storeService.getAll();
		return new ResponseEntity<List<StoreResDto>>(responses, HttpStatus.OK);
	}
}
