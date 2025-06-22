package com.jakmall.jakscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakmall.jakscraper.dto.soldproduct.SoldProductResDto;
import com.jakmall.jakscraper.service.SoldProductService;

@RestController
@RequestMapping("/solds")
public class SoldProductController {

	@Autowired
	private SoldProductService soldProductService;
	
	@GetMapping
	public ResponseEntity<List<SoldProductResDto> > getAll(){
		final List<SoldProductResDto> responses = soldProductService.getAll();
		return new ResponseEntity<List<SoldProductResDto>>(responses, HttpStatus.OK);
	}
}
