package com.godoro.ecommerce.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoro.ecommerce.business.dto.CategoryDto;
import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.business.dto.ProductFilter;
import com.godoro.ecommerce.data.entity.Category;
import com.godoro.ecommerce.data.entity.Product;
import com.godoro.ecommerce.data.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public long create(ProductDto productDto) {
		
		Product product = toEntity(productDto);
		
		productRepository.save(product);
		
		productDto.setProductId(product.getProductId());
		
		return productDto.getProductId();
		
	}
	
	@Override
	public void update(ProductDto productDto) {
		
		Product product = toEntity(productDto);
		
		productRepository.save(product);
	}

	@Override
	public void delete(long id) {
		
		productRepository.deleteById(id);
	}
	
	@Override
	public ProductDto find(long productId) {
		
		Optional<Product> optional = productRepository.findById(productId);
		ProductDto productDto = null;
		
		if(optional.isPresent()) {
			Product product = optional.get();
			productDto = toDto(product);
		}
		
		return productDto;
	}

	@Override
	public Iterable<ProductDto> findAllByCategoryId(long categoryId) {
		
		return toDtos(productRepository.findAllByCategoryId(categoryId));
	}
	
	@Override
	public Iterable<ProductDto> findAllByPriceBetween(double minPrice, double maxPrice) {
		
		return toDtos(productRepository.findAllByPriceBetween(minPrice, maxPrice));
	}

	@Override
	public Iterable<ProductDto> findAllByNameLike(String namePattern) {
		
		return toDtos(productRepository.findAllByNameLike(namePattern));
	}

	@Override
	public Iterable<ProductDto> search(ProductFilter productFilter) {
		
		return toDtos(productRepository.search(
				productFilter.getCategoryId(),
				productFilter.getNamePattern(),
				productFilter.getMinPrice(),
				productFilter.getMaxPrice()));
	}
	
	private Product toEntity(ProductDto productDto) {
		
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setSalesPrice(productDto.getSalesPrice());
		if(productDto.getCategory() != null) {
			Category category = new Category();
			category.setCategoryId(productDto.getCategory().getCategoryId());
			category.setCategoryName(productDto.getCategory().getCategoryName());
			product.setCategory(category);
		}
		
		
		return product;
	}

	private ProductDto toDto(Product product) {
		
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setSalesPrice(product.getSalesPrice());
		if(product.getCategory() != null) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryId(product.getCategory().getCategoryId());
			categoryDto.setCategoryName(product.getCategory().getCategoryName());
			productDto.setCategory(categoryDto);
		}
		
		
		return productDto;
	}
	
	private List<ProductDto> toDtos(Iterable<Product> products) {
		
		List<ProductDto> productDtos = new ArrayList<>(); 

		for(Product product: products)
			productDtos.add(toDto(product));

		return productDtos;
	}

}
