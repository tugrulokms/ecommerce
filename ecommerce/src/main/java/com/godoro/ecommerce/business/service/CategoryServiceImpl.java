package com.godoro.ecommerce.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoro.ecommerce.business.dto.CategoryDto;
import com.godoro.ecommerce.data.entity.Category;
import com.godoro.ecommerce.data.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public CategoryDto find(long id) {
		
		Optional<Category> optional = categoryRepository.findById(id);
		CategoryDto categoryDto = null;
		
		if(optional.isPresent()) {
			categoryDto = toDto(optional.get());
		}
		
		return categoryDto;
	}
	
	public Iterable<CategoryDto> findAll() {
		
		return toDtos(categoryRepository.findAll());
	}

	@Override
	public Iterable<CategoryDto> findAllByParentId(long parentId) {
		
		return toDtos(categoryRepository.findAllByParentId(parentId));
	}
	
	@Override
	public Iterable<CategoryDto> findTopCategories() {
		
		return toDtos(categoryRepository.findTopCategories());
	}
	
	
	private CategoryDto toDto(Category category) {
		
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(category.getCategoryId());
		categoryDto.setCategoryName(category.getCategoryName());
		if(category.getParentCategory() != null && category.getParentCategory().getCategoryId() != 0) {
			CategoryDto parentDto = new CategoryDto();
			parentDto.setCategoryId(category.getParentCategory().getCategoryId());
			parentDto.setCategoryName(category.getParentCategory().getCategoryName());
			
			categoryDto.setParentDto(parentDto);
		}
		
		return categoryDto;
	}
	
	private List<CategoryDto> toDtos(Iterable<Category> categories) {
		
		List<CategoryDto> categoryDtos = new ArrayList<>();
		
		for(Category category: categories)
			categoryDtos.add(toDto(category));
		
		return categoryDtos;
	}
	
}
