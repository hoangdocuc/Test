package com.hoangdocuc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.hoangdocuc.dto.CategoryDTO;

public interface ICategoryService {
	Map<String,String> findAll();
	List<CategoryDTO> findAll(Pageable pageable);
	List<CategoryDTO> findAllCategory();
	List<CategoryDTO> find4All();
	int getTotalItems();
	CategoryDTO findById(long id);
	CategoryDTO save(CategoryDTO categoryDTO);
	void delete(long[] ids);
}
