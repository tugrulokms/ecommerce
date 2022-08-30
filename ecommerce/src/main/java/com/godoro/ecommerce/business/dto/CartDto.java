package com.godoro.ecommerce.business.dto;

import java.util.List;

public class CartDto {
	
	private long cartId;
	private List<CartProductDto> cartProducts;
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartProductDto> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProductDto> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
}
