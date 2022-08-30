package com.godoro.ecommerce.business.service;

import java.util.List;

import com.godoro.ecommerce.business.dto.CartProductDto;
import com.godoro.ecommerce.business.dto.ProductDto;

public interface CartProductService {

	long add(ProductDto productDto);
	
	void remove(long id);
	
	CartProductDto findByProductId(long productId);
	
	List<CartProductDto> findAllByCart(long cartId);
	
}
