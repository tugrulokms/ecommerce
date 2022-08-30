package com.godoro.ecommerce.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.godoro.ecommerce.data.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("select p from Product p where p.category.categoryId = :categoryId")
	Iterable<Product> findAllByCategoryId(@Param("categoryId") long categoryId);
	
	@Query("select p from Product p where p.salesPrice > :minPrice "
			+ "and p.salesPrice < :maxPrice")
	Iterable<Product> findAllByPriceBetween(
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice
			);
	
	@Query("select p from Product p where p.productName like %:namePattern%")
	Iterable<Product> findAllByNameLike(@Param("namePattern") String namePattern);
	
	@Query("select p from Product p where p.category.categoryId = :categoryId "
			+ "and (:namePattern = '' or p.productName like %:namePattern%) "
			+ "and (p.salesPrice > :minPrice and p.salesPrice < :maxPrice)")
	Iterable<Product> search(
			@Param("categoryId") long categoryId,
			@Param("namePattern") String namePattern,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice
			);
}