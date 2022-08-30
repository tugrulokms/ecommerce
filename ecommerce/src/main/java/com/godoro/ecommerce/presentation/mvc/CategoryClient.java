package com.godoro.ecommerce.presentation.mvc;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.godoro.ecommerce.business.dto.CategoryDto;
import com.godoro.ecommerce.business.dto.ProductDto;

@Controller
public class CategoryClient {

	@GetMapping("/category/{id}")
	public String find(Model model, @PathVariable("id") long categoryId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8080/api/category/" + categoryId;
		CategoryDto categoryDto = restTemplate.getForObject(url, CategoryDto.class);
		model.addAttribute("categoryDto", categoryDto);
		
		String urlSubs = "http://localhost:8080/api/categories/sub/" + categoryId;
		ResponseEntity<List<CategoryDto>> responseSub = restTemplate.exchange(
				urlSubs, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<CategoryDto>>() {});
		List<CategoryDto> subCategoryDtos = responseSub.getBody();
		model.addAttribute("subCategoryDtos" ,subCategoryDtos);
		
		String urlProducts = "http://localhost:8080/api/products/" + categoryId;
		ResponseEntity<List<ProductDto>> responseProducts = restTemplate.exchange(
				urlProducts, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<ProductDto>>() {});
		List<ProductDto> productDtos = responseProducts.getBody();
		model.addAttribute("productDtos" ,productDtos);
		
		return "CategoryPage";
	}
	
	@GetMapping("/categories")
	public String findAll(Model model) {
		
		String url = "http://localhost:8080/api/categories/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<CategoryDto>>() {});
		
		List<CategoryDto> categoryDtos = response.getBody();
		
		model.addAttribute("categoryDtos", categoryDtos);
		
		String message = "Kategori Listesi: ";
		
		model.addAttribute("message", message);
		
		return "CategoriesPage";
	}
	
	@GetMapping("/categories/top")
	public String findTop(Model model) {
		
		String url = "http://localhost:8080/api/categories/top";
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<CategoryDto>>() {});
		
		List<CategoryDto> categoryDtos = response.getBody();
		
		model.addAttribute("categoryDtos", categoryDtos);
		
		String message = "Kategori Listesi: ";
		
		model.addAttribute("message", message);
		
		return "CategoriesPage";
	}
	
	@GetMapping("/categories/sub/{id}")
	public String findSub(Model model, @PathVariable("id") long parentId) {
		
		String url = "http://localhost:8080/api/categories/sub/" + parentId;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<CategoryDto>>() {});
		
		List<CategoryDto> categoryDtos = response.getBody();
		
		model.addAttribute("categoryDtos", categoryDtos);
		
		String message = "Kategori Listesi: ";
		
		model.addAttribute("message", message);
		
		return "CategoriesPage";
	}
	
}
