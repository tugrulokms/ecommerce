package com.godoro.ecommerce.data.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = -1186647693806065934L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String productName;
	private double salesPrice;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
