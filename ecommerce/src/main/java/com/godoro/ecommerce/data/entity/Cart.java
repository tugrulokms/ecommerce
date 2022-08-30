package com.godoro.ecommerce.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart implements Serializable {

	private static final long serialVersionUID = -684903714382796268L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	private List<CartProduct> cartProducts;
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	
}
