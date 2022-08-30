package com.godoro.ecommerce.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.ecommerce.business.dto.CartDto;
import com.godoro.ecommerce.business.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/api/cart/")
	public CartDto getCart() {
		
		return cartService.get((long) 1);
		
	}

}
