package com.godoro.ecommerce.business.service;

import com.godoro.ecommerce.business.dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto find(long id);
	
	Iterable<CategoryDto> findAll();
	
	Iterable<CategoryDto> findAllByParentId(long parentId);
	
	Iterable<CategoryDto> findTopCategories();
}
