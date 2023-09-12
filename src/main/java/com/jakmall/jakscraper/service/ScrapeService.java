package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.SupplierDao;
import com.jakmall.jakscraper.dto.product.ProductResDto;
import com.jakmall.jakscraper.model.Product;
import com.jakmall.jakscraper.model.Supplier;

@Service
public class ScrapeService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SupplierDao supplierDao;
	
	
	@Transactional
	public List<ProductResDto> scrape() {
		final List<Supplier> supplier = supplierDao.getSuppliers();
		final List<Product> products = new ArrayList<>();
		final List<ProductResDto> responses = new ArrayList<>();
		
		for(Supplier s : supplier) {
			try {
				Document doc = Jsoup.connect(s.getUrl()).get();
				String data = doc.data().toString();
				int startIndex = data.lastIndexOf("result = ");
				int lastIndex = data.indexOf("var config");
				JSONObject json = new JSONObject(data.substring(startIndex+8, lastIndex));
				
				JSONArray productArr = json.getJSONArray("products");
				
				for(int i = 0; i < productArr.length(); i++) {
					JSONArray productSku = productArr.getJSONObject(i).getJSONArray("sku");
					for(int j = 0; j < productSku.length(); j++) {					
						Product product = new Product();
						product.setSupplier(s);
						product.setSku(productSku.getJSONObject(j).getString("sku"));
						product.setPrice(Long.valueOf(productSku.getJSONObject(j).getLong("final_price")));
						product.setInStock(Boolean.valueOf(productSku.getJSONObject(j).getBoolean("in_stock")));
						product.setCreatedBy(0L);
						products.add(product);
					}
				}	
				
				if(json.getJSONObject("pagination").getJSONObject("links").optJSONObject("last") == null) {	
					JSONObject pagination = json.getJSONObject("pagination").getJSONObject("links").getJSONObject("first");
					for(int i = 2; i <= pagination.length(); i++) {					
						products.addAll(extendScrape(s, s.getUrl()+"?page="+i));
					}
				}else {
					JSONObject pagination = json.getJSONObject("pagination").getJSONObject("links").getJSONObject("last");
					Integer lastPage = Integer.valueOf(pagination.keys().next());
					for(int i = 2; i <= lastPage+1; i++) {					
						products.addAll(extendScrape(s, s.getUrl()+"?page="+i));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		productService.insertProduct(products);		
		
		for(Product product : products) {
			final ProductResDto response = new ProductResDto();
			response.setSku(product.getSku());
			response.setPrice(product.getPrice());
			response.setInStock(product.getInStock());				
			response.setSupplierName(product.getSupplier().getSupplierName());
			responses.add(response);
		}
		return responses;
	}
	
	public List<Product> extendScrape(Supplier supplier, String url) {
		final List<Product> products = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			String data = doc.data().toString();
			int startIndex = data.lastIndexOf("result = ");
			int lastIndex = data.indexOf("var config");
			JSONObject json = new JSONObject(data.substring(startIndex+8, lastIndex));
			
			JSONArray productArr = json.getJSONArray("products");

			for(int i = 0; i < productArr.length(); i++) {
				JSONArray productSku = productArr.getJSONObject(i).getJSONArray("sku");
				for(int j = 0; j < productSku.length(); j++) {					
					Product product = new Product();
					product.setSupplier(supplier);
					product.setSku(productSku.getJSONObject(j).getString("sku"));
					product.setPrice(Long.valueOf(productSku.getJSONObject(j).getLong("final_price")));
					product.setInStock(Boolean.valueOf(productSku.getJSONObject(j).getBoolean("in_stock")));
					product.setCreatedBy(0L);
					products.add(product);
				}
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
