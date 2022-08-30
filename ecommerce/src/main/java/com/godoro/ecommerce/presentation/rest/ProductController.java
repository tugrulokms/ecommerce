package com.godoro.ecommerce.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.business.dto.ProductFilter;
import com.godoro.ecommerce.business.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/api/product")
	public long create(@RequestBody ProductDto productDto) {
		
		long result = productService.create(productDto);
		
		return result;
	}
	
	@PutMapping("/api/product")
	public void update(@RequestBody ProductDto productDto)  {
		
		productService.update(productDto);
	}
	
	@DeleteMapping("/api/product/{id}")
	public void delete(@PathVariable("id") long productId) {
		
		productService.delete(productId);
	}
	
	@GetMapping("/api/product/{id}")
	public ProductDto find(@PathVariable("id") @RequestBody long productId) {
		
		return productService.find(productId);
	}
	
	@GetMapping("/api/products/{categoryId}")
	public Iterable<ProductDto> findAllByCategoryId(
			@PathVariable("categoryId") long categoryId) 
	{
		
		return productService.findAllByCategoryId(categoryId);
	}
	
	@GetMapping("/api/products/between/{min}/{max}")
	public Iterable<ProductDto> findAllByPriceBetween(
			@PathVariable("min") double minPrice,
			@PathVariable("max") double maxPrice) 
	{
		
		return productService.findAllByPriceBetween(minPrice, maxPrice);
	}
	
	@GetMapping("/api/products/like/{pattern}")
	public Iterable<ProductDto> findAllByNameLike(@PathVariable("pattern") String namePattern) {
		
		return productService.findAllByNameLike(namePattern);
	}
	
	@PostMapping("/api/products/search")
	public Iterable<ProductDto> search(@RequestBody ProductFilter productFilter) {
		
		System.out.println(productFilter.getNamePattern());
		
		return productService.search(productFilter);
	}
}
