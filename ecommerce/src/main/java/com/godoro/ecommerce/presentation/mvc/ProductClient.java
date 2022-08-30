package com.godoro.ecommerce.presentation.mvc;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.business.dto.ProductFilter;

@Controller
public class ProductClient {

	@PostMapping("/product")
	public String create(Model model, ProductDto product, BindingResult result)
	{
		String url = "http://localhost:8080/api/product/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		model.addAttribute("product", product);
		
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println(error.toString());
			}
			return "ProductEditor";
		}
		
		long productId = restTemplate.postForObject(url, product, Long.class);
		
		product.setProductId(productId);
		
		System.out.println("Saklandı: " + product.getProductId());
		
		model.addAttribute("message", "Ürün başarıyla eklendi.");
		
		return "ProductSuccess";
	}
	
	@PutMapping("/product")
	public String update(Model model, ProductDto product, BindingResult result) {
		
		String url = "http://localhost:8080/api/product";
		
		RestTemplate restTemplate = new RestTemplate();
		
		model.addAttribute("product", product);
		
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors())
				System.out.println(error.toString());
			return "ProductEditor";
		}
		
		restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(product), Void.class);
		
		System.out.println("Güncellendi: " + product.getProductId());
		
		return "ProductSuccess";
	}
	
	@GetMapping("/product/delete")
	public void delete() {
		
		long id = 7;
		
		String url = "http://localhost:8080/api/product/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(id), Void.class);
		
		System.out.println(id + " numaralı ürün silindi");
	}
	
	@GetMapping("/product/find/{id}")
	public String find(Model model, @PathVariable("id") long productId) {
		
		String url = "http://localhost:8080/api/product/" + productId;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ProductDto productDto = restTemplate.getForObject(url, ProductDto.class);
	
		String message = "Bulunan ürün: ";
		
		model.addAttribute("message", message);
		model.addAttribute("productDto", productDto);
		
		return "ProductPage";
	}
	
	@GetMapping("/products/by/{id}")
	public String findAllByCategoryId(Model model, @PathVariable("id") long categoryId) {
		
		String url = "http://localhost:8080/api/products/" + categoryId;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<ProductDto>>() {});
		
		List<ProductDto> productDtos = response.getBody();
		
		model.addAttribute("productDtos" ,productDtos);
		
		String message = "Bulunan ürünler (" + categoryId + " numaralı katalogdakiler): ";
		
		model.addAttribute("message" ,message);
		
		return "ProductsPage";
		
	}
	
	@GetMapping("/products/between/{min}/{max}")
	public void findAllByPriceBetween(
			@PathVariable("min") double minPrice,
			@PathVariable("max") double maxPrice) 
	{
		
		String url = "http://localhost:8080/api/products/between/" + minPrice + "/" + maxPrice;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<ProductDto>>() {});
		
		List<ProductDto> resultList = response.getBody();
		
		System.out.println("Bulunan ürünler(" + minPrice + " - " + maxPrice + " aralığında): ");
		System.out.println("-----------------");
		
		for (ProductDto productDto : resultList) {
			System.out.println("Ürün no: " + productDto.getProductId());
			System.out.println("Ürün adı: " + productDto.getProductName());
			System.out.println("Ürün fiyatı: " + productDto.getSalesPrice());
			System.out.println("Ürün katalog no: " + productDto.getCategory().getCategoryId());
			System.out.println("Ürün katalog adı: " + productDto.getCategory().getCategoryName());
			System.out.println("");
		}
	}
	
	@GetMapping("/products/like/{pattern}")
	public void findAllByNameLike(@PathVariable("pattern") String namePattern) {
		
		String url = "http://localhost:8080/api/products/like/" + namePattern;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				url, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<ProductDto>>() {});
		
		List<ProductDto> resultList = response.getBody();
		
		System.out.println("Bulunan ürünler(" + namePattern + " içerenler): ");
		System.out.println("-----------------");
		
		for (ProductDto productDto : resultList) {
			System.out.println("Ürün no: " + productDto.getProductId());
			System.out.println("Ürün adı: " + productDto.getProductName());
			System.out.println("Ürün fiyatı: " + productDto.getSalesPrice());
			System.out.println("Ürün katalog no: " + productDto.getCategory().getCategoryId());
			System.out.println("Ürün katalog adı: " + productDto.getCategory().getCategoryName());
			System.out.println("");
		}
	}
	
	@GetMapping("/products/search")
	@ResponseBody
	public String search() {
		
		String url = "http://localhost:8080/api/products/search";
		
		RestTemplate restTemplate = new RestTemplate();
		
		ProductFilter productFilter = new ProductFilter();
		productFilter.setCategoryId(1);
		productFilter.setNamePattern("");
		productFilter.setMinPrice(500);
		productFilter.setMaxPrice(5000);
		
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				url, HttpMethod.POST, new HttpEntity<>(productFilter),
				new ParameterizedTypeReference<List<ProductDto>>() {});
		
		List<ProductDto> resultList = response.getBody();
		
		System.out.println("Bulunan ürünler(Gelişmiş aramaya göre gelenler): ");
		System.out.println("-----------------");
		
		for (ProductDto productDto : resultList) {
			System.out.println("Ürün no: " + productDto.getProductId());
			System.out.println("Ürün adı: " + productDto.getProductName());
			System.out.println("Ürün fiyatı: " + productDto.getSalesPrice());
			System.out.println("Ürün katalog no: " + productDto.getCategory().getCategoryId());
			System.out.println("Ürün katalog adı: " + productDto.getCategory().getCategoryName());
			System.out.println("");
		}
		
		return "Konsola bak";
	}
	
}
