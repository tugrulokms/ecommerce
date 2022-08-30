package com.godoro.ecommerce.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoro.ecommerce.business.dto.CartDto;
import com.godoro.ecommerce.data.entity.Cart;
import com.godoro.ecommerce.data.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartProductService cartProductService;
	
	@Override
	public CartDto get(long id) {
		
		Optional<Cart> optional = cartRepository.findById(id);
		CartDto cartDto = new CartDto();
		
		if(optional.isPresent()) {
			Cart cart = optional.get();
			cartDto.setCartId(cart.getCartId());
			cartDto.setCartProducts((cartProductService.findAllByCart(cart.getCartId())));
		} else {
			Cart cart = new Cart();
			cartRepository.save(cart);
			cartDto.setCartId(cart.getCartId());
		}
		
		return cartDto;
	}

}
