package com.godoro.ecommerce.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = -7416543377989111213L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;
	private String categoryName;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "parent_category_id", nullable = false)
	private Category parentCategory;
	@OneToMany(mappedBy = "parentCategory",
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Category> childCategories;
	@OneToMany(mappedBy = "category",
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> products;

	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<Category> getChildCategories() {
		return childCategories;
	}
	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
