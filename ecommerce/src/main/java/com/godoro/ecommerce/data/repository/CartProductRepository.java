package com.godoro.ecommerce.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.godoro.ecommerce.data.entity.CartProduct;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {
	
	@Query("select cp from CartProduct cp where cp.product.productId = :productId")
	CartProduct findByProductId(@Param("productId") long productId);
	
	@Query("select cp from CartProduct cp where cp.cart.cartId = :cartId")
	Iterable<CartProduct> findAllByCart(@Param("cartId") long cartId);

}