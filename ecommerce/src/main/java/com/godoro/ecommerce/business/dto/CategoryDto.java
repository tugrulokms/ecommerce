package com.godoro.ecommerce.business.dto;

public class CategoryDto {

	private long categoryId;
	private String categoryName;
	private CategoryDto parentCategory;
 	
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
	public CategoryDto getParentCategory() {
		return parentCategory;
	}
	public void setParentDto(CategoryDto parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
