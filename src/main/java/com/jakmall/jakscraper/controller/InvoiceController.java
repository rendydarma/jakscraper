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
import com.jakmall.jakscraper.dto.invoice.InvoiceInsertReqDto;
import com.jakmall.jakscraper.dto.invoice.InvoiceReqDto;
import com.jakmall.jakscraper.dto.invoice.InvoiceResDto;
import com.jakmall.jakscraper.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@GetMapping("/store")
	public ResponseEntity<List<InvoiceResDto>> getAllByStore(@RequestBody InvoiceReqDto data){
		final List<InvoiceResDto> responses = invoiceService.getAllByStore(data);
		return new ResponseEntity<List<InvoiceResDto>>(responses, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<InvoiceResDto>> getAll(){
		final List<InvoiceResDto> responses = invoiceService.getAll();
		return new ResponseEntity<List<InvoiceResDto>>(responses, HttpStatus.CREATED);
	}
	
	@PostMapping()
	public ResponseEntity<InsertResDto> insert(@RequestBody InvoiceInsertReqDto data){
		final InsertResDto response = invoiceService.insert(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.OK);
	}
}
