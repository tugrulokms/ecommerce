package com.godoro.ecommerce.presentation.mvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.godoro.ecommerce.business.dto.ProductDto;

@Controller
public class CartProductClient {
	
	@PostMapping("/cart-product")
	public String addCartProduct(Model model, ProductDto product, BindingResult result) {
		
		String url = "http://localhost:8080/api/cart-product/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		model.addAttribute("product", product);
		
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println(error.toString());
			}
			return "CartPage";
		}
		
		long id = restTemplate.postForObject(url, product, Long.class);
		
		System.out.println("Eklendi: " + id);
		
		return "CartPage";
	}
	
	@GetMapping("/cart-product/delete/{id}")
	public String removeCartProduct(Model model, @PathVariable("id") long cartProductId) {	

		String url = "http://localhost:8080/api/cart-product/" + cartProductId;
		
		RestTemplate restTemplate = new RestTemplate();
		
		model.addAttribute("cartProductId", cartProductId);
		
		restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(cartProductId), Void.class);
		
		System.out.println();
		
		return "CartPage";
	}

}
