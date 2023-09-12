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
import com.jakmall.jakscraper.dto.supplier.SupplierInsertReqDto;
import com.jakmall.jakscraper.dto.supplier.SupplierResDto;
import com.jakmall.jakscraper.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@GetMapping()
	public ResponseEntity<List<SupplierResDto>> getAll(){
		final List<SupplierResDto> responses = supplierService.getAll();
		return new ResponseEntity<List<SupplierResDto>>(responses, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<InsertResDto> insert(@RequestBody SupplierInsertReqDto data){
		final InsertResDto response = supplierService.insert(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}
}
