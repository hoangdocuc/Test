package com.hoangdocuc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdocuc.entity.CategoryEntity;

public interface CategoryReposiory extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
}
