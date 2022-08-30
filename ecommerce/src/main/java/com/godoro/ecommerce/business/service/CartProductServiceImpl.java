package com.godoro.ecommerce.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoro.ecommerce.business.dto.CartDto;
import com.godoro.ecommerce.business.dto.CartProductDto;
import com.godoro.ecommerce.business.dto.ProductDto;
import com.godoro.ecommerce.data.entity.Cart;
import com.godoro.ecommerce.data.entity.CartProduct;
import com.godoro.ecommerce.data.entity.Product;
import com.godoro.ecommerce.data.repository.CartProductRepository;
import com.godoro.ecommerce.data.repository.CartRepository;

@Service
public class CartProductServiceImpl implements CartProductService {
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public long add(ProductDto productDto) {
		
		CartProduct cartProduct = cartProductRepository.findByProductId(productDto.getProductId());
		
		if(cartProduct != null) {
			
			System.out.println("Cart ID: " + cartProduct.getCart().getCartId());
			cartProduct.setQuantity(cartProduct.getQuantity() + 1);
			cartProduct.setSalesPrice(cartProduct.getSalesPrice() + productDto.getSalesPrice());
			cartProductRepository.save(cartProduct);
			
		} else {
			
			CartProductDto cartProductDto = new CartProductDto();
			
			Optional<Cart> optional = cartRepository.findById((long) 1);
			
			CartDto cartDto = new CartDto();
			
			if(optional.isPresent() ) {
				Cart cart = optional.get();
				cartDto.setCartId(cart.getCartId());
			}
			
			cartProductDto.setCart(cartDto);
			cartProductDto.setProduct(productDto);
			cartProductDto.setSalesPrice(productDto.getSalesPrice());
			cartProductDto.setQuantity(1);
			cartProduct = toEntity(cartProductDto);
			cartProductRepository.save(cartProduct);
		}
		
		System.out.println("Aşağıdaki ürün sepete eklendi:");
		System.out.println("-----------------------------");
		System.out.println("Ürün adı: " + cartProduct.getProduct().getProductName());
		System.out.println("Sayısı: " + cartProduct.getQuantity());
		System.out.println("Satış fiyatı: " + cartProduct.getSalesPrice());
		
		return cartProduct.getCartProductId();
	}

	@Override
	public void remove(long id) {
		
		Optional<CartProduct> optional = cartProductRepository.findById(id);
		CartProduct cartProduct= new CartProduct();
		
		if(optional.isPresent()) {
			cartProduct = optional.get();
			if(cartProduct.getQuantity() == 1) {
				cartProductRepository.deleteById(id);
				System.out.println(id + " numaralı ürün sepetten kaldırılmıştır.");
			} else {
				cartProduct.setQuantity(cartProduct.getQuantity() - 1);
				cartProduct.setSalesPrice(cartProduct.getSalesPrice() - cartProduct.getProduct().getSalesPrice());
				cartProductRepository.save(cartProduct);
			}
		}
		
	}
	
	@Override
	public CartProductDto findByProductId(long productId) {
		
		return toDto(cartProductRepository.findByProductId(productId));
	}
	
	@Override
	public List<CartProductDto> findAllByCart(long cartId) {
		
		return toDtos(cartProductRepository.findAllByCart(cartId));
	}
	
	private CartProduct toEntity(CartProductDto cartProductDto) {
		
		CartProduct cartProduct = new CartProduct();
		cartProduct.setCartProductId(cartProductDto.getCartProductId());
		cartProduct.setQuantity(cartProductDto.getQuantity());
		
		if(cartProductDto.getCart() != null) {
			
			Cart cart = new Cart();
			Optional<Cart> optional = cartRepository.findById(cartProductDto.getCart().getCartId());
				
			if(optional.isPresent()) {
				cart = optional.get();
			}
			cartProduct.setCart(cart);
		}
		
		if(cartProductDto.getProduct() != null) {
			
			Product product = new Product();
			product.setProductId(cartProductDto.getProduct().getProductId());
			product.setProductName(cartProductDto.getProduct().getProductName());
			product.setSalesPrice(cartProductDto.getProduct().getSalesPrice());
			
			cartProduct.setProduct(product);
		}
		
		cartProduct.setSalesPrice(cartProduct.getQuantity() * cartProductDto.getSalesPrice());
		
		return cartProduct;
	}
	
	private CartProductDto toDto(CartProduct cartProduct) {
		
		CartProductDto cartProductDto = new CartProductDto();
		cartProductDto.setCartProductId(cartProduct.getCartProductId());
		cartProductDto.setQuantity(cartProduct.getQuantity());
		cartProductDto.setSalesPrice(cartProduct.getSalesPrice());
		
		if(cartProduct.getCart() != null) {
			CartDto cartDto = new CartDto();
			cartDto.setCartId(cartProduct.getCart().getCartId());
			
			cartProductDto.setCart(cartDto);
		}
		
		if(cartProduct.getProduct() != null) {
			ProductDto productDto = new ProductDto();
			productDto.setProductId(cartProduct.getProduct().getProductId());
			productDto.setProductName(cartProduct.getProduct().getProductName());
			productDto.setSalesPrice(cartProduct.getProduct().getSalesPrice());
			
			cartProductDto.setProduct(productDto);

		}
		
		return cartProductDto;
	}
	
	private List<CartProductDto> toDtos(Iterable<CartProduct> cartProducts) {
		
		List<CartProductDto> cartProductDtos = new ArrayList<>();
		
		for (CartProduct cartProduct : cartProducts) {
			cartProductDtos.add(toDto(cartProduct));
		}
		
		return cartProductDtos;
	}

}
