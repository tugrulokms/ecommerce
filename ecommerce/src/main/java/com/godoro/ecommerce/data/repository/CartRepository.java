package com.godoro.ecommerce.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.godoro.ecommerce.data.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
	
}
