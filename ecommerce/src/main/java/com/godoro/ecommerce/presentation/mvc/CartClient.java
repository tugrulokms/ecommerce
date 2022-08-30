package com.godoro.ecommerce.presentation.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.godoro.ecommerce.business.dto.CartDto;

@Controller
public class CartClient {

	@GetMapping("/cart")
	public String getCart(Model model) {
		
		String url = "http://localhost:8080/api/cart/";
		
		RestTemplate restTemplate = new RestTemplate();		
		
		CartDto cart = restTemplate.getForObject(url, CartDto.class);
		
		model.addAttribute("cart", cart);
		
		return "CartPage";
	}
	
}
