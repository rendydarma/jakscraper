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
		
		final List<ProductResDto> latestProducts = productService.getAll();
		for(ProductResDto p : latestProducts) {
			try {
				Document doc = Jsoup.connect("https://www.jakmall.com/search?q=" + p.getSku())
					    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					    .header("Accept-Language", "en-US,en;q=0.9")
					    .timeout(10000)
					    .get();
				String data = doc.data().toString();
				int startIndex = data.lastIndexOf("result = ");
				int lastIndex = data.indexOf("var config");
				JSONObject json = new JSONObject(data.substring(startIndex+8, lastIndex));
				
				JSONArray productArr = json.getJSONArray("products");
				
				if(productArr.length() == 0) {
					Product product = new Product();
					product.setProductName(p.getProductName());
					product.setPrice(p.getPrice());
					product.setSku(p.getSku());
					product.setInStock(false);
					product.setCreatedBy(0L);
					products.add(product);
					System.out.println(product.getSku());
				}else {					
					for(int i = 0; i < productArr.length(); i++) {
						JSONArray productSku = productArr.getJSONObject(i).getJSONArray("sku");
						for(int j = 0; j < productSku.length(); j++) {					
							Product product = new Product();
							product.setProductName(productArr.getJSONObject(i).getString("name"));
							product.setSku(productSku.getJSONObject(j).getString("sku"));
							product.setPrice(Long.valueOf(productSku.getJSONObject(j).getLong("final_price")));
							product.setPrice(roundUpToNearest((product.getPrice() * 40/100) + product.getPrice() + 10000,100));
							product.setInStock(Boolean.valueOf(productSku.getJSONObject(j).getBoolean("in_stock")));
							product.setCreatedBy(0L);
							products.add(product);
							System.out.println(product.getSku());
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		for(Supplier s : supplier) {
//			try {
//				Document doc = Jsoup.connect(s.getUrl()).get();
//				String data = doc.data().toString();
//				int startIndex = data.lastIndexOf("result = ");
//				int lastIndex = data.indexOf("var config");
//				JSONObject json = new JSONObject(data.substring(startIndex+8, lastIndex));
//				
//				JSONArray productArr = json.getJSONArray("products");
//				
//				for(int i = 0; i < productArr.length(); i++) {
//					JSONArray productSku = productArr.getJSONObject(i).getJSONArray("sku");
//					for(int j = 0; j < productSku.length(); j++) {					
//						Product product = new Product();
//						product.setSupplier(s);
//						product.setProductName(productArr.getJSONObject(i).getString("name"));
//						product.setSku(productSku.getJSONObject(j).getString("sku"));
//						product.setPrice(Long.valueOf(productSku.getJSONObject(j).getLong("final_price")));
//		                product.setPrice(roundUpToNearest((product.getPrice() * 40/100) + product.getPrice() + 10000,100));
//						product.setInStock(Boolean.valueOf(productSku.getJSONObject(j).getBoolean("in_stock")));
//						product.setCreatedBy(0L);
//						products.add(product);
//						System.out.println(product.getSku());
//					}
//				}	
//				
//				if(json.getJSONObject("pagination").getJSONObject("links").optJSONObject("last") == null) {	
//					JSONObject pagination = json.getJSONObject("pagination").getJSONObject("links").getJSONObject("first");
//					for(int i = 2; i <= pagination.length(); i++) {					
//						products.addAll(extendScrape(s, s.getUrl()+"?page="+i));
//					}
//				}else {
//					JSONObject pagination = json.getJSONObject("pagination").getJSONObject("links").getJSONObject("last");
//					Integer lastPage = Integer.valueOf(pagination.keys().next());
//					for(int i = 2; i <= lastPage+1; i++) {					
//						products.addAll(extendScrape(s, s.getUrl()+"?page="+i));
//					}
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
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

	public Long roundUpToNearest(Long value, int i) {
	    return (long) (Math.ceil(value / i) * i);
	}
	
	public List<Product> extendScrape(Supplier supplier, String url) {
		final List<Product> products = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url)
				    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				    .header("Accept-Language", "en-US,en;q=0.9")
				    .timeout(10000)
				    .get();
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
					product.setProductName(productArr.getJSONObject(i).getString("name"));
					product.setSku(productSku.getJSONObject(j).getString("sku"));
					product.setPrice(Long.valueOf(productSku.getJSONObject(j).getLong("final_price")));
					product.setPrice((long) Math.ceil((product.getPrice() * 40/100) + product.getPrice() + 9200));
					product.setInStock(Boolean.valueOf(productSku.getJSONObject(j).getBoolean("in_stock")));
					product.setCreatedBy(0L);
					products.add(product);
					System.out.println(product.getSku());
				}
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
