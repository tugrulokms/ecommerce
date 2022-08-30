package com.godoro.ecommerce.business.service;

import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.business.dto.ProductFilter;

public interface ProductService {
	
	long create(ProductDto productDto);
	
	void update(ProductDto productDto);
	
	void delete(long id);

	ProductDto find(long productId);
	
	Iterable<ProductDto> findAllByCategoryId(long categoryId);
	
	Iterable<ProductDto> findAllByPriceBetween(double minPrice, double maxPrice);
	
	Iterable<ProductDto> findAllByNameLike(String namePattern);
	
	Iterable<ProductDto> search(ProductFilter productFilter);
}