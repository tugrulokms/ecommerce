package com.godoro.ecommerce.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.ecommerce.business.dto.CategoryDto;
import com.godoro.ecommerce.business.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/category/{id}")
	public CategoryDto find(@PathVariable("id") long categoryId) {
		
		return categoryService.find(categoryId);
	}
	
	@GetMapping("/api/categories")
	public Iterable<CategoryDto> findAll() {
		
		return categoryService.findAll();
	}
	
	@GetMapping("/api/categories/sub/{id}")
	public Iterable<CategoryDto> findSub(@PathVariable("id") long parentId) {
		
		return categoryService.findAllByParentId(parentId);
	}
	
	@GetMapping("/api/categories/top")
	public Iterable<CategoryDto> findTop() {
		
		return categoryService.findTopCategories();
	}

}
