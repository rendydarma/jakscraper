package com.jakmall.jakscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakmall.jakscraper.model.Product;
import com.jakmall.jakscraper.service.ScrapeService;

@RestController
@RequestMapping("/scrape")
public class ScrapeController {

	@Autowired
	private ScrapeService scrapeService;
	
	@GetMapping
	public ResponseEntity<List<Product>> scrape(){
		final List<Product> responses = scrapeService.scrape();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	
}
