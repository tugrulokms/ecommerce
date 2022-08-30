package com.godoro.ecommerce.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.ecommerce.business.dto.CartProductDto;
import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.business.service.CartProductService;

@RestController
public class CartProductController {
	
	@Autowired
	private CartProductService cartProductService;
	
	@GetMapping("/api/cart-products")
	public List<CartProductDto> getCartProducts() {
		
		return cartProductService.findAllByCart(1);
	}

	@PostMapping("/api/cart-product")
	public long add(@RequestBody ProductDto productDto) {
		
		return cartProductService.add(productDto);
		
	}
	
	@DeleteMapping("/api/cart-product/{id}")
	public void remove(@PathVariable("id") long cartProductId) {
		
		cartProductService.remove(cartProductId);
		
	}
}
