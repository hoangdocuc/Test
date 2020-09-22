package com.hoangdocuc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	List<ProductEntity> findByCategory(CategoryEntity category);
	List<ProductEntity> findByNameContaining(String name);
}
