package com.jakmall.jakscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakmall.jakscraper.dto.file.FileReqDto;
import com.jakmall.jakscraper.dto.file.FileResDto;
import com.jakmall.jakscraper.service.ExcelService;

@RestController
@RequestMapping("/excels")
public class ExcelController {

	@Autowired
	private ExcelService excelService;
	
	@PostMapping
	public ResponseEntity<List<FileResDto>> insertTemplate(@RequestBody List<FileReqDto> files){
		final List<FileResDto> responses = excelService.insertTemplate(files);
		return new ResponseEntity<List<FileResDto>>(responses, HttpStatus.OK);
	}
}