package com.jakmall.jakscraper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_sold_product")
public class SoldProduct extends BaseModel{

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "qty")
	private Long qty;
	
	@Column(name = "grand_total")
	private Long grandTotal;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Long getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
