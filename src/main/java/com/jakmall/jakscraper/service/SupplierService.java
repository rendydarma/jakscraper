package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.SupplierDao;
import com.jakmall.jakscraper.dto.InsertResDto;
import com.jakmall.jakscraper.dto.supplier.SupplierInsertReqDto;
import com.jakmall.jakscraper.dto.supplier.SupplierResDto;
import com.jakmall.jakscraper.model.Supplier;

@Service
public class SupplierService {

	@Autowired
	private SupplierDao supplierDao;

	public List<SupplierResDto> getAll() {
		final List<Supplier> suppliers = supplierDao.getSuppliers();
		final List<SupplierResDto> responses = new ArrayList<>();
		for (Supplier s : suppliers) {
			final SupplierResDto response = new SupplierResDto();
			response.setId(s.getId());
			response.setSupplierName(s.getSupplierName());
			response.setUrl(s.getUrl());
			responses.add(response);
		}
		return responses;
	}

	@Transactional
	public InsertResDto insert(SupplierInsertReqDto data) {
		final Supplier supplier = new Supplier();
		supplier.setSupplierName(data.getSupplierName());
		supplier.setUrl(data.getUrl());
		supplier.setCreatedBy(0L);

		supplierDao.insert(supplier);

		final InsertResDto response = new InsertResDto();
		response.setId(supplier.getId());
		response.setMessage("Data has inserted");

		return response;
	}
}
