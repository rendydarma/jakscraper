package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.ProductDao;
import com.jakmall.jakscraper.dao.SoldProductDao;
import com.jakmall.jakscraper.dto.soldproduct.SoldProductInsertReqDto;
import com.jakmall.jakscraper.dto.soldproduct.SoldProductResDto;
import com.jakmall.jakscraper.model.Product;
import com.jakmall.jakscraper.model.SoldProduct;

@Service
public class SoldProductService {

	@Autowired
	private SoldProductDao soldProductDao;
	
	@Autowired
	private ProductDao productDao;
	
	public List<SoldProductResDto> getAll(){
		final List<SoldProduct> soldProducts = soldProductDao.getAll();
		final List<SoldProductResDto> responses = new ArrayList<>();
		for(SoldProduct s : soldProducts) {
			final SoldProductResDto response = new SoldProductResDto();
			response.setProductName(s.getProduct().getProductName());
			response.setSku(s.getProduct().getSku());
			response.setQty(s.getQty());
			response.setGrandTotal(s.getGrandTotal());
			responses.add(response);
		}
		return responses;
	}

	@Transactional
	public SoldProduct insert(SoldProductInsertReqDto data) {
		try {
			SoldProduct soldProduct = new SoldProduct();
			soldProduct = soldProductDao.getBySku(data.getSku());
			if (soldProduct != null) {
				SoldProduct soldProductDb = soldProductDao.getById(soldProduct.getId());
				soldProductDb.setQty(soldProduct.getQty() + data.getQty());
				soldProductDb.setGrandTotal(soldProduct.getGrandTotal() + data.getGrandTotal());
			} else {
				soldProduct = new SoldProduct();
				final Product product = productDao.getProduct(data.getSku());
				soldProduct.setProduct(product);
				soldProduct.setQty(data.getQty());
				soldProduct.setGrandTotal(data.getGrandTotal());
				soldProduct.setCreatedBy(0L);
				soldProductDao.insert(soldProduct);
			}

			return soldProduct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
