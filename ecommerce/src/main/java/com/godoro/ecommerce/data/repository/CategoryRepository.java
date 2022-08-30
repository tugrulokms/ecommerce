package com.godoro.ecommerce.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.godoro.ecommerce.data.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	@Query("select c from Category c where c.parentCategory.categoryId = :parentId")
	Iterable<Category> findAllByParentId(@Param("parentId") long parentId);
	
	@Query("select c from Category c where c.parentCategory.categoryId = 0")
	Iterable<Category> findTopCategories();
}
