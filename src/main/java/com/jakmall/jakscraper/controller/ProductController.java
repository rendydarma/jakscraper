package com.jakmall.jakscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakmall.jakscraper.dto.product.ProductResDto;
import com.jakmall.jakscraper.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public ResponseEntity<List<ProductResDto>> getAll(){
		final List<ProductResDto> responses = productService.getAll();
		return new ResponseEntity<List<ProductResDto>>(responses, HttpStatus.OK);
	}
	
}
